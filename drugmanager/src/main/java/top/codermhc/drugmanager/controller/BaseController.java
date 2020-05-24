package top.codermhc.drugmanager.controller;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.service.UserVOService;
import top.codermhc.drugmanager.vo.UserVO;

/**
 * 提供用户登录后的一些基本操作
 *
 * @author Ye Minghui
 */
@Controller
public class BaseController {

    /**
     * admin 角色的id
     */
    public static final Integer ROLE_ADMIN_ID = 1;
    @Resource(name = "userVOServiceImpl")
    private UserVOService userVOService;

    /**
     * 获取当前的认证对象
     * @return authentication
     */
    public UserAuthentication authentication() {
        return (UserAuthentication) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 注销当前对象
     */
    public void doLogout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 设置用户的登录信息
     *
     * @param model Model
     */
    public void setLoginInfo(Model model) {
        UserAuthentication authentication = authentication();
        UserVO login_info = userVOService.getById(authentication.getUserId());
        model.addAttribute("login_info", login_info);
    }

    /**
     * 设置分页信息
     *
     * @param model      Model
     * @param current    当前页
     * @param size       页大小
     * @param itemsCount 总行数
     */
    public void setPage(Model model, Integer current, Integer size, int itemsCount) {
        model.addAttribute("current", current);
        model.addAttribute("size", size);
        model.addAttribute("count", itemsCount);
        int pageCount = itemsCount / size + (itemsCount % size != 0 ? 1 : 0);
        model.addAttribute("pageCount", pageCount);
    }

    /**
     * 支持的操作
     */
    protected List<String> funcOps = Arrays.asList("list", "add", "del", "modify", "info");

    /**
     * 验证操作选项
     *
     * @param func  操作标识
     * @param model Model
     */
    protected void validFunc(String func, Model model) {
        if (!funcOps.contains(func)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "操作不支持");
        }
        model.addAttribute("func", func);
    }
}
