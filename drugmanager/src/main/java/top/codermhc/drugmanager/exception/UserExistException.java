package top.codermhc.drugmanager.exception;

/**
 * 用户已存在异常
 *
 * @author Ye Minghui
 */
public class UserExistException extends DrugmanagerException{

    public UserExistException() {
        super("用户已存在");
    }

}
