package br.com.trajy.graphql.util;

import java.util.function.Supplier;

public final class TrainWreckUtil {

    private TrainWreckUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static <T> T nullIfWreck(Supplier<T> in) {
        try {
            return in.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static void nullIfWreck(Runnable in) {
        try {
            in.run();
        } catch (NullPointerException e) {
            Runnable runnable = () -> {
            };
            runnable.run();
        }
    }

}
