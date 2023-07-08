package br.com.trajy.graphql.controller;

import static java.time.Duration.ofSeconds;
import static java.time.LocalTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static reactor.core.publisher.Flux.interval;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsSubscription;
import org.reactivestreams.Publisher;

@DgsComponent
public class WebSocketController {

    @DgsSubscription
    public Publisher<String> time() {
        return interval(ofSeconds(10)).map(t -> now().format(ofPattern("HH:mm:ss")));
    }

}
