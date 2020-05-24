package top.codermhc.drugmanager.exception;

/**
 * 用户被锁定
 *
 * @author Ye Minghui
 */
public class UserLockedException extends DrugmanagerException{

    public UserLockedException() {
        super("用户已锁定");
    }
}
