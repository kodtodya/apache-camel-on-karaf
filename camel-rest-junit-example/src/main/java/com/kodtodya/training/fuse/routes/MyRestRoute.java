package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.Exchange;

public class MyRestRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("direct:restCall").to("log:?level=INFO&showBody=true")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_URI, simple("https://reqres.in/api/users"))
                .to("https://test-http-uri").to("log:myLogger?level=INFO&showBody=true");
    }
}
