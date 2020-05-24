package top.codermhc.drugmanager.config;

import com.google.gson.Gson;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Resource;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class GsonRedisSerializer<T> implements RedisSerializer<T> {

    public GsonRedisSerializer() {
    }

    @Resource(name = "myGson")
    private Gson gson;

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        String typeName = t.getClass().getTypeName();
        return typeName.concat("::").concat(gson.toJson(t)).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String s = new String(bytes, DEFAULT_CHARSET);
        String[] split = s.split("::");
        try {
            return (T) gson.fromJson(split[1], Class.forName(split[0]));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) gson.fromJson(split[1], Object.class);
    }

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
}
