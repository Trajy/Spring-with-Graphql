package br.com.trajy.graphql.util;

import lombok.experimental.UtilityClass;
import java.util.function.Supplier;

@UtilityClass
public class TrainWreckUtil {

    public <T> T nullIfWreck(Supplier<T> in) {
        try {
            return in.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

}
