package top.codermhc.drugmanager.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.codermhc.drugmanager.base.entity.UserAuthentication;
import top.codermhc.drugmanager.shiro.CustomByteSource;

@Configuration
public class GsonConfig {

    @Bean
    public Gson myGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SimpleAuthenticationInfo.class, new JsonSerializer<SimpleAuthenticationInfo>() {
            @Override
            public JsonElement serialize(SimpleAuthenticationInfo src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject object = new JsonObject();
                object.add("principals", context.serialize(src.getPrincipals()));
                object.add("credentials",context.serialize(src.getCredentials()));
                object.add("credentialsSalt",context.serialize(src.getCredentialsSalt()));
                return object;
            }
        });
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
            @Override
            public void write(JsonWriter out, LocalDateTime value) throws IOException {
                out.value(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value));
            }

            @Override
            public LocalDateTime read(JsonReader in) throws IOException {
                return LocalDateTime.parse(in.nextString());
            }
        }.nullSafe());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
            @Override
            public void write(JsonWriter out, LocalDate value) throws IOException {
                out.value(DateTimeFormatter.ISO_LOCAL_DATE.format(value));
            }

            @Override
            public LocalDate read(JsonReader in) throws IOException {
                return LocalDate.parse(in.nextString());
            }
        }.nullSafe());

        gsonBuilder.registerTypeAdapter(PrincipalCollection.class, new JsonDeserializer<SimplePrincipalCollection>() {
            @Override
            public SimplePrincipalCollection deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
                Map<String, Set<UserAuthentication>> realmPrincipals = context
                    .deserialize(json.getAsJsonObject().get("realmPrincipals"),
                        new TypeToken<Map<String, Set<UserAuthentication>>>() {
                        }.getType());
                SimplePrincipalCollection collection = null;
                try {
                    Field field = SimplePrincipalCollection.class.getDeclaredField("realmPrincipals");
                    field.setAccessible(true);
                    collection = new SimplePrincipalCollection();
                    field.set(collection, realmPrincipals);
                    field.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return collection;
            }});
        gsonBuilder.registerTypeAdapter(ByteSource.class, new JsonDeserializer<ByteSource>() {
            @Override
            public ByteSource deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
                return context.deserialize(json, new TypeToken<CustomByteSource>() {
                }.getType());
            }
        });
        return gsonBuilder.create();
    }
}
