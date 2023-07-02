package br.com.trajy.graphql.util;

import java.util.function.Supplier;

public final class TrainWreckUtil {

    private TrainWreckUtil() { }

    public static <T> T nullIfWreck(Supplier<T> in) {
        try {
            return in.get();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
