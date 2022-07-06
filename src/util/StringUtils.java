package util;

public class StringUtils {
    public static boolean isEmpty(String value) {
        if (value == null || value == "") {
            return true;
        }
        return false;
    }
}
