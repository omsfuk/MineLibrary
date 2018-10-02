package cn.omsfuk.library.web.util;

import java.util.HashMap;
import java.util.Map;

public abstract class MapUtil {

    public static Map<String, Object> singleValueMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return map;
    }
}
