package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;

public class EncryptionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        // read messages from jms & send to file
        from("file:{{input.file.location}}")
                .convertBodyTo(String.class)
                .marshal().pgp("{{encryption.public-key.path}}", "{{encryption.public-key.user-id}}")
                .to("file:{{encrypted.file.location}}?fileName=myFile.pgp");
    }
}