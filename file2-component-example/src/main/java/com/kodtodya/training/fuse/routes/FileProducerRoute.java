package com.kodtodya.training.fuse.routes;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;

public class FileProducerRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("direct:myDirectComponent")
                .id("directConsumer")
                .log("\n\nHi, Your file content in file producer is>>\n\n ${body}")
                .to("file:{{output.location}}?fileName=camel-session-out.txt&fileExist=Append")
                .id("fileProducer");
    }

}