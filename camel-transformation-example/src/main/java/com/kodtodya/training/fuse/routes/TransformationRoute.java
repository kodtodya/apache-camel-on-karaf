package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.builder.RouteBuilder;

public class TransformationRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(InvalidPayloadException.class)
                .to("log:exceptionLogger");

        validator()
                .type("xml")
                .withUri("validator:xsd/pacs.008.001.09.xsd");

        // read messages from jms & send to file
        from("file:{{input.file.location}}").routeId("xml-to-direct-route")
                .unmarshal().jacksonxml().validate().body()
                .to("direct:convertToJson");

        from("direct:convertToJson").routeId("direct-to-json-route")
                .marshal().json(true)
                .to("file:{{output.file.location}}?fileName={{output.file.name}}");
    }
}