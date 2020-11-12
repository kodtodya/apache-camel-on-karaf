package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;

public class FileConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:{{input.location}}?noop=true&initialDelay=2000&bufferSize=512")
                .id("fileConsumer")

                .log("\n\nHi, file content received in consumer is>>\n\n ${body}").id("logger-in-consumer")

                .to("direct:myDirectComponent?timeout=5000&failIfNoConsumers=false")
                .id("directProducer");
    }
}