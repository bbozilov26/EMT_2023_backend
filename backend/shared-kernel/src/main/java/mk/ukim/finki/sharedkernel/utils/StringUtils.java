package mk.ukim.finki.sharedkernel.utils;

public class StringUtils {
    public static String toLowerCaseIfNotNull(String value) {
        return NullableUtils.getIfNotNull(value, String::toLowerCase);
    }

    public static String toNullIfEmpty(String value) {
        return value == null || value.isEmpty() ? null : value;
    }
}
