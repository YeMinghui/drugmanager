package top.codermhc.drugmanager.shiro;

import java.io.Serializable;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;

public class CustomByteSource implements ByteSource, Serializable {

    public CustomByteSource() {
    }

    public CustomByteSource(byte[] bytes) {
        this.bytes = bytes;
    }

    public CustomByteSource(String src) {
        this.bytes = src.getBytes();
    }

    private byte[] bytes;

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toHex() {
        return Hex.encodeToString(bytes);
    }

    @Override
    public String toBase64() {
        return Base64.encodeToString(bytes);
    }

    @Override
    public boolean isEmpty() {
        return bytes == null || bytes.length == 0;
    }
}
