package br.com.trajy.graphql.exception;


import com.netflix.graphql.dgs.exceptions.DgsBadRequestException;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;

public class ConditionUtils {

    private ConditionUtils() { }

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
}
