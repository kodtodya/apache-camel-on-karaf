package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;

public class HttpRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:myTimer?period=2000")
                .to("https://www.google.com?q=kodtodya")
                .log("content received from https invocation is: ${body}");
    }
}