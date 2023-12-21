package br.com.trajy.graphql.util;

import static java.util.Objects.nonNull;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtil {

    public String toStringNullSafe(Object object) {
        return nonNull(object) ? object.toString() : null;
    }

}
