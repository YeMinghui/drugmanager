package top.codermhc.drugmanager.shiro;

import java.util.HashMap;
import java.util.Locale;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import top.codermhc.drugmanager.utils.WebUtil;

@Slf4j
public class CustomLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);

        log.info("logout {},",subject);

        // Check if POST only logout is enabled
        if (isPostOnlyLogout()) {
            // check if the current request's method is a POST, if not redirect
            if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
                return onLogoutRequestNotAPost(request, response);
            }
        }

        //try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "success");
        WebUtil.writeJson(map,response);
        return false;
    }
}
