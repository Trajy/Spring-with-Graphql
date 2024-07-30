package br.com.trajy.graphql.util;

import static br.com.trajy.graphql.exception.ConditionUtils.checkBadRequest;
import static java.util.Objects.isNull;
import static reactor.core.publisher.Sinks.many;

import br.com.trajy.graphql.codegen.tad.Message;
import br.com.trajy.graphql.codegen.tad.MessageValue;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Sinks.Many;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to handle GraphQlSubscriptions
 * @author Trajy
 */
public final class DgsSubscriptionUtil {

    private static final Map<Class<?>, Many<Message>> sinks = new HashMap<>();

    private DgsSubscriptionUtil() { }

    /**
     * publish general
     * @param topic topic message from Enum
     * @param value value of message
     * @param <E> topic of enum with message topics
     * @author Trajy
     */
    public static <T extends MessageValue, E extends Enum<E>> T publish(T value, E topic) {
        singletonSink(Enum.class).tryEmitNext(
                Message.builder()
                        .setTopic(topic.name())
                        .setValue(value)
                        .build()
        );
        return value;
    }

    /**
     * publish by Enum topics type
     * @param topic topic message from Enum
     * @param value value of message
     * @param <E> type of enum with message topics
     * @author Trajy
     */
    public static <T extends MessageValue, E extends Enum<E>> T publishByType(T value, E topic) {
        singletonSink(topic.getClass()).tryEmitNext(
                Message.builder()
                        .setTopic(topic.name())
                        .setValue(value)
                        .build()
        );
        return value;
    }

    /**
     * Subscribe in general Publiser
     * @author Trajy
     */
    public static Publisher<Message> subscribe() {
        return  singletonSink(Enum.class).asFlux();
    }

    /**
     * Subscribe in Publiser by Enum topics type
     * @param topicClazz class of enum with message topics
     * @param <E> type of enum with message topics
     * @author Trajy
     */
    public static <E extends Enum<E>> Publisher<Message> subscribe(Class<E> topicClazz) {
        return  singletonSink(topicClazz).asFlux();
    }

    private static <E extends Enum<E>> Many<Message> singletonSink(Class<E> topicClazz) {
        checkBadRequest(isNull(topicClazz), "Subscribe topic clazz is required");
        Many<Message> sink = sinks.get(topicClazz);
        if(isNull(sink)) {
            sink = many().multicast().onBackpressureBuffer();
            sinks.put(topicClazz, sink);
        }
        return sink;
    }

}
