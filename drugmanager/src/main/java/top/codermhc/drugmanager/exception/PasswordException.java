package top.codermhc.drugmanager.exception;

/**
 * 用户密码不正确，仅当修改密码等操作时，告诉用户。不做安全拦截。
 *
 * @author Ye Minghui
 */
public class PasswordException extends DrugmanagerException{

    public PasswordException() {
        super("密码不正确");
    }
}
