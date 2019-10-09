package io.copymaker.racing.util;

public class StrUtil {

    public static String repeat(String str, int count) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        if (count <= 0) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        int i = count;
        while (i-- > 0) {
            sb.append(str);
        }
        return sb.toString();
    }

}
