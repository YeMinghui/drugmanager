package top.codermhc.drugmanager.exception;

/**
 * 用药管理系统业务异常, 所有业务异常继承自它。
 *
 * @author Ye Minghui
 */
public class DrugmanagerException extends RuntimeException{

    public DrugmanagerException() {
    }

    public DrugmanagerException(String message) {
        super(message);
    }

    public DrugmanagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrugmanagerException(Throwable cause) {
        super(cause);
    }

    public DrugmanagerException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
