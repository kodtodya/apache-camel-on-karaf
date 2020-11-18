package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestToJmsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        restConfiguration()
                .component("netty-http")
                .host("0.0.0.0")
                .port(8888)
                .bindingMode(RestBindingMode.auto);

        // use the rest DSL to define the rest services
        rest("/fuse-demo")
                .post("/sendMessage")
                .consumes("application/text")
                .id("test-rest-service")
                .to("direct:myDirectComponent");

        // send message to jms server
        from("direct:myDirectComponent")
                .setExchangePattern(ExchangePattern.InOnly)
                // ensuring maximum of 10 messages are sent in any 10 second window
                .throttle(10).timePeriodMillis(10000)
                .to("jms:queue:fuse-jms-demo");

    }
}