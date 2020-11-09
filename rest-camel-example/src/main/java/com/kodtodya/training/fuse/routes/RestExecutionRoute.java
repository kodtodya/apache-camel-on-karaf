package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestExecutionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");
        // call the embedded rest service from the Any Controller
        restConfiguration().component("jetty").port(8888).bindingMode(RestBindingMode.auto);

        rest().get("/checkprime/{number}").produces("application/json")
                .to("direct:prime");

        from("direct:prime")
                .setBody().simple("${header.number}")
                .bean("primeNumberService");
    }
}