package top.codermhc.drugmanager.exception;

/**
 * 用户已禁用
 *
 * @author Ye Minghui
 */
public class UserDisabledException extends DrugmanagerException {

    public UserDisabledException() {
        super("用户被禁用");
    }
}
