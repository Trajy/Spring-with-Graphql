package br.com.trajy.graphql.exception;


import com.netflix.graphql.dgs.exceptions.DgsBadRequestException;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import lombok.SneakyThrows;

public class ThrowExceptionUtils {

    private ThrowExceptionUtils() {
        nonInstanciableClazz(getClass());
    }

    public static void checkEntityNotFound(boolean condition, String message) {
        if(condition) {
            throw new DgsEntityNotFoundException(message);
        }
    }

    public static void checkBadRequest(boolean condition, String message) {
        if(condition) {
            throw new DgsBadRequestException(message);
        }
    }

    @SneakyThrows(value = InstantiationException.class)
    public static void nonInstanciableClazz(Class<?> clazz) {
        throw new InstantiationException(
                clazz.getSimpleName().concat(" is a non-instanciable class because it is a utility clazz")
        );
    }
}
