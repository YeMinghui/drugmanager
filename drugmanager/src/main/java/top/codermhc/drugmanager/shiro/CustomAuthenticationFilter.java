package top.codermhc.drugmanager.shiro;

import java.util.Objects;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import top.codermhc.drugmanager.utils.ResponseCode;
import top.codermhc.drugmanager.utils.ResponseData;

public class CustomAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if (Objects.equals(principal, null)) {
            ResponseData result = new ResponseData(HttpStatus.UNAUTHORIZED, "未登录", null);
            top.codermhc.drugmanager.utils.WebUtil.writeJson(result, response);
        }
        // 前后端分离，不用shiro跳转
        return false;
    }

}
