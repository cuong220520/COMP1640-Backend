package com.greenwich.comp1640.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Util {
    private Util() {

    }

    public static String gen13UUID() {
        UUID uuid = UUID.randomUUID();
        return Long.toString(uuid.getLeastSignificantBits(), Character.MAX_RADIX).replace("-", "");
    }

    public static boolean isNotBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static Map<String, Object> splitQuery(String queryParams) throws UnsupportedEncodingException {
        Map<String, Object> queryPairs = new LinkedHashMap<>();
        String[] pairs = queryParams.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.name()),
                    URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.name()));
        }
        return queryPairs;
    }
}
