package com.libraryproject.springbootlibrary.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {

    public static String payloadExtraction(String token, String extraction) {
        token = token.replace("Bearer ", "");

        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] payloadEntries = payload.split(",");

        Map<String, String> payloadMap = new HashMap<>();

        for (String entries : payloadEntries) {
            String[] keyValue = entries.split(":");
            if (keyValue[0].equals(extraction)) {
                int remove = 1;

                if (keyValue[1].endsWith("}")) {
                    remove = 2;
                }

                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);
                keyValue[1] = keyValue[1].substring(1);

                payloadMap.put(keyValue[0], keyValue[1]);
            }
        }

        if (payloadMap.containsKey(extraction)) return payloadMap.get(extraction);

        return null;
    }
}
