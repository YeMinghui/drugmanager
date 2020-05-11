package top.codermhc.drugmanager.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 默认加密工具
 *
 * @author Ye Minghui
 */
public class PasswordHash {

    private PasswordHash() {
    }

    public static String hash(String password, String salt) {
        SimpleHash hash = new SimpleHash("SHA-256", password, ByteSource.Util.bytes(salt), 2);
        return hash.toString();
    }

}
