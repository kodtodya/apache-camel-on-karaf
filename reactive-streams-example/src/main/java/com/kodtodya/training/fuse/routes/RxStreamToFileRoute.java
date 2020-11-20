package com.kodtodya.training.fuse.routes;

import io.reactivex.Flowable;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.reactive.streams.api.CamelReactiveStreams;
import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;
import org.reactivestreams.Publisher;

public class RxStreamToFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        CamelReactiveStreamsService camel = CamelReactiveStreams.get(this.getContext());

        // Getting a stream of Strings from reactive streams
        Publisher<String> streams = camel.fromStream("file-content", String.class);

        // read messages from rx-streams & send to file
        Flowable.fromPublisher(streams)
                .doOnNext(e -> camel.to("{{file.component.uri}}",e))
                .doOnNext(System.out::println)
                .subscribe();
    }
}