package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;

public class QuartzToStreamRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("quartz://myQuartzGroup/myQuartz?cron={{cron.value}}").id("quartzConsumer")
                .autoStartup(true)
                .setBody().simple("{{log-body.value}} ${header.fireTime}")
                .to("stream:out").id("streamOutProducer");
    }
}