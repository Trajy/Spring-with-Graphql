package br.com.trajy.graphql.util;

import static java.util.Objects.isNull;
import static reactor.core.publisher.Sinks.many;

import com.netflix.graphql.dgs.exceptions.DgsBadRequestException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks.Many;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to handle GraphQlSubscriptions by topic enum model
 * @author Trajy
 */
public final class SubscriptionGraphQlUtil {

    private static final Map<Class<?>, Many<SubscriptionModel<?, ?>>> sinks = new HashMap<>();

    private SubscriptionGraphQlUtil() { }

    /**
     * Subscribe in Publiser by Enum topics type
     * @param type topic message from Enum
     * @param value value of message
     * @param <T> type of message value
     * @param <E> type of enum with message topics
     * @author Trajy
     */
    public static  <T, E extends Enum<E>> T publish(E type, T value) {
        singletonSink(type.getClass()).tryEmitNext(
                SubscriptionModel.<Object, E>builder()
                        .topic(type)
                        .model(value)
                        .build()
        );
        return value;
    }

    /**
     * Subscribe in Publiser by Enum topics type
     * @param type class of enum with message topics
     * @param <T> type of message value
     * @param <E> type of enum with message topics
     * @author Trajy
     */
    public static <T, E extends Enum<E>> Publisher<SubscriptionModel<T, E>> subscribe(Class<E> type) {
        return  (Publisher) singletonSink(type).asFlux();
    }

    private static  <T, E extends Enum<E>> Many<SubscriptionModel<T, E>> singletonSink(Class<E> type) {
        if(isNull(type)) {
            throw new DgsBadRequestException("Subscribe type is required");
        }
        Many<SubscriptionModel<T, E>> sink = (Many) sinks.get(type);
        if(isNull(sink)) {
            sink = many().multicast().onBackpressureBuffer();
            sinks.put(type, (Many) sink);
        }
        return sink;
    }

    /**
     * Simple DTO (data transfer object) with metadata for GraphQl subscriptions
     * @param <T> type of message value
     * @param <E> type of enum with message topics
     * @author Trajy
     */
    @Getter
    @Setter
    @ToString
    @SuperBuilder
    public static class SubscriptionModel<T, E extends Enum<E>> {

        private E topic;

        private T model;

    }

}
