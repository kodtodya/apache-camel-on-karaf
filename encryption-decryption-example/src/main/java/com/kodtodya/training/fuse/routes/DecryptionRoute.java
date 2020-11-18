package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class DecryptionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        from("file:{{encrypted.file.location}}?fileName=myFile.pgp&noop=true")

                .unmarshal().pgp("{{encryption.private-key.path}}",
                "{{encryption.private-key.user-id}}",
                "{{encryption.private-key.password}}")

                .recipientList(constant("direct:directFileProducer,direct:directLogger"))
                .parallelProcessing();

        from("direct:directFileProducer")
                .to("file:{{decrypted.file.location}}?fileName=myFile.txt");

        from("direct:directLogger")
                .to("log:myLogger");
    }
}