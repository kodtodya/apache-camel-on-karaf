package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;

public class KafkaToRxStreamRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        // read messages from kafka with 25 concurrent consumers & send to file
        from("{{kafka.component.uri}}&consumerStreams=25")
                .log("{{log.content}}")
                .toD("{{dynamic.reactive.component}}");
                //.to("file:{{file.output.location}}?fileName={{output.file-name}}&fileExist=Append");
    }
}