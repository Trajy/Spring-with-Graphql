package br.com.trajy.graphql.util;

import lombok.experimental.UtilityClass;
import java.util.Objects;

@UtilityClass
public class CommonUtil {

    public String toStringNullSafe(Object object) {
        return Objects.toString(object, null);
    }

}
