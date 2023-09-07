package mk.ukim.finki.sharedkernel.utils;

import com.sun.istack.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

public class NullableUtils {
    public static <T, R> R getIfNotNull(T obj, @NotNull Function<T, R> operation, @NotNull Supplier<R> orElse) {
        if (obj == null) {
            return orElse.get();
        }
        return operation.apply(obj);
    }

    public static <T, R> R getIfNotNull(T obj, @NotNull Function<T, R> operation) {
        if (obj == null) {
            return null;
        }

        return operation.apply(obj);
    }

    public static <T> T getIfNotNullOrElse(T obj, @NotNull T orElse) {
        return obj != null ? obj : orElse;
    }
    public static <T, R> R getIfNotNullOrElse(T obj, @NotNull Function<T, R> operation, @NotNull R orElse) {
        if (obj == null) {
            return orElse;
        }

        return operation.apply(obj);
    }
}
