package top.codermhc.drugmanager.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.AdviceFilter;
import top.codermhc.drugmanager.utils.WebUtil;

/**
 * 前后端分离，分开部署，后台只支持Ajax访问
 *
 * @author Ye Minghui
 */
@Slf4j
public class AjaxFilter extends AdviceFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (log.isDebugEnabled()) {
            log.debug("A request url=[{}], method=[{}].", httpServletRequest.getRequestURL(), httpServletRequest.getMethod());
        }
        // 如果不是Ajax请求，直接返回
        if (!WebUtil.isAjaxRequest(httpServletRequest)) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("只支持Ajax请求");
            return false;
        }
        
        return super.preHandle(request, response);
    }
}
