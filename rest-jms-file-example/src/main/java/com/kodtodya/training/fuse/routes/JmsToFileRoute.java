package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;

public class JmsToFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        // read messages from jms & send to file
        from("jms:queue:fuse-jms-demo?concurrentConsumers=5")
                .to("file:/home/kodtodya/Downloads/_test?fileName=jms-to-file.txt&fileExist=Append");
    }
}