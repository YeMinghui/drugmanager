package top.codermhc.drugmanager.controller;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import top.codermhc.drugmanager.base.entity.User;
import top.codermhc.drugmanager.exception.TokenExpiredException;
import top.codermhc.drugmanager.service.UserVOService;
import top.codermhc.drugmanager.vo.UserVO;

/**
 * 提供用户登录后的一些基本操作
 *
 * @author Ye Minghui
 */
public class BaseController {

    /**
     * admin 角色的id
     */
    public static final Integer ROLE_ADMIN_ID = 1;
    @Resource(name = "userVOServiceImpl")
    private UserVOService userVOService;

    /**
     * 获取当前user
     * @return user
     */
    public User user() {
        return (User) subjectAuthenticated().getPrincipal();
    }

    /**
     * 判断当前对象是否拥有admin角色
     * @return 是返回true
     */
    public boolean isAdmin() {
        return subjectAuthenticated().hasRole("admin");
    }

    /**
     * 获取当前subject, 并检测授权, 抛出授权过期
     * @return
     */
    public Subject subjectAuthenticated() {
        Subject subject = subject();
        if (!subject.isAuthenticated()) {
            throw new TokenExpiredException();
        }
        return subject;
    }

    /**
     * 获取Subject
     * @return
     */
    public Subject subject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 注销当前对象
     */
    public void doLogout() {
        subjectAuthenticated().logout();
    }

    /**
     * 设置用户的登录信息
     *
     * @param model Model
     */
    public void setLoginInfo(Model model) {
        UserVO login_info = userVOService.getById(user().getId());
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
