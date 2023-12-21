package br.com.trajy.graphql.util;

import static java.util.Objects.nonNull;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.net.URL;

@UtilityClass
public class UrlUtil {

    @SneakyThrows
    public URL UriInstance(String uri) {
        return new URL(uri);
    }

    public String toStringNullSafe(URL url) {
        return nonNull(url) ? url.toString() : null;
    }

}
