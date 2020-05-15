package top.codermhc.drugmanager.utils;

import java.util.UUID;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 默认盐生成器
 *
 * @author Ye Minghui
 */
public class SaltGenerator {

    private SaltGenerator() {
    }

    public static String generate() {
        return new SimpleHash("SHA-256", UUID.randomUUID().toString()).toString();
    }

}
