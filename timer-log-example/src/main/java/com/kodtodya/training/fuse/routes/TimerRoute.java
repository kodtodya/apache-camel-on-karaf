package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;

public class TimerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:myTimer?period=1000").id("timer-component-source")
                .autoStartup(true)
                .setBody().simple("Current time is: ${date:now:yyyy-MM-dd HH:mm:ss}")
                .to("vm:vmComponent").id("vm-component-producer");

        from("vm:vmComponent").id("vm-component-consumer")
                .log("${body}")
                .to("log:myLogger").id("logger-component-producer");
    }
}