package top.codermhc.drugmanager.utils;

/**
 * 用户状态
 *
 * @author Ye Minghui
 */
public final class UserStatus {

    private UserStatus() {
    }

    public static final int ENABLE = 1;
    public static final int LOCKED = 2;

    /**
     * 检测是否启用用户
     *
     * @param status 状态
     * @return true 为可用
     */
    public static boolean enable(int status) {
        return (status & ENABLE) != 0;
    }

    /**
     * 检测用户是否锁定
     *
     * @param status 状态
     * @return true 为锁定
     */
    public static boolean locked(int status) {
        return (status & LOCKED) != 0;
    }

    /**
     * 清除相应状态
     *
     * @param status 状态
     * @param userStatus 要清除的状态
     * @return 新的状态
     */
    public static Integer unset(Integer status, int userStatus) {
        return status & (~userStatus);
    }

}
