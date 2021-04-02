package utils;

import java.util.UUID;

public class UUIDUtil {
    public static String get32UUID() {
        return getUUID(false, true);
    }

    public static String getUUID(boolean isUpperCase, boolean isHyphenReplaced) {
        String uuidString = UUID.randomUUID().toString();
        if (isHyphenReplaced) {
            uuidString = uuidString.replace("-", "");
        }

        if (isUpperCase) {
            uuidString = uuidString.toUpperCase();
        }

        return uuidString;
    }
}
