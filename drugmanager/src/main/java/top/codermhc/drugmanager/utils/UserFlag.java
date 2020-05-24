package top.codermhc.drugmanager.utils;

public enum UserFlag {

    ENABLED, LOCKED, DISABLED;

    public final int mask;

    UserFlag() {
        this.mask = 1 << ordinal();
    }

    public static boolean isEnabled(int mask, UserFlag flag) {
        return (mask & flag.mask) != 0;
    }

    public static int config(int mask, UserFlag flag, boolean enable) {
        if (enable) {
            mask |= flag.mask;
        } else {
            mask &= ~flag.mask;
        }
        return mask;
    }

    public static int enable(int mask, UserFlag flag) {
        return config(mask, flag, true);
    }

    public static int disable(int mask, UserFlag flag) {
        return config(mask, flag, false);
    }

}
