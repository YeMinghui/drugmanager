package top.codermhc.drugmanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesParser {

    public Map<String, String> parse(String fileName) {
        InputStream inputStream = PropertiesParser.class.getClassLoader().getResourceAsStream(fileName);
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> map = new HashMap<>();
            properties.forEach((key, value) -> map.put(key.toString(), value.toString()));
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Collections.emptyMap();
    }
}
