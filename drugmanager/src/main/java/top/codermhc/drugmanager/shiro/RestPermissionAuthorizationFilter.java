package top.codermhc.drugmanager.shiro;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import top.codermhc.drugmanager.utils.ResponseData;

@Slf4j
public class RestPermissionAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String requestURI = this.getPathWithinApplication(request);
        String[] strings = path.split("--");
        if (strings.length <= 1) {
            // 普通的 URL, 正常处理
            return this.pathsMatch(strings[0], requestURI);
        } else {
            // 获取当前请求的 http method.
            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();
            // 匹配当前请求的 http method 与 过滤器链中的的是否一致
            return httpMethod.equals(strings[1].toUpperCase()) && this.pathsMatch(strings[0], requestURI);
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        // 如果未登录
        if (subject.getPrincipal() == null) {
            if (log.isDebugEnabled()) {
                log.debug("用户: [{}] 请求 restful url : {}, 未登录被拦截.", subject.getPrincipal(),
                    this.getPathWithinApplication(request));
            }
            ResponseData data = new ResponseData(HttpStatus.UNAUTHORIZED, "未登录", null);
            top.codermhc.drugmanager.utils.WebUtil.writeJson(data, response);
        } else {
            // 如果已登陆, 但没有权限
            if (log.isDebugEnabled()) {
                log.debug("用户: [{}] 请求 restful url : {}, 无权限被拦截.", subject.getPrincipal(),
                    this.getPathWithinApplication(request));
            }

            ResponseData data = new ResponseData(HttpStatus.FORBIDDEN, "无权限", null);
            top.codermhc.drugmanager.utils.WebUtil.writeJson(data, response);
        }
        return false;
    }
}
