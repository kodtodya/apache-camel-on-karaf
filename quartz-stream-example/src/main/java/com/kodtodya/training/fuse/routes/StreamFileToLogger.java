package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;

public class StreamFileToLogger extends RouteBuilder {
    @Override
    public void configure() throws Exception {
		from("stream:file?fileName={{stream.filename}}&{{stream.options}}").id("streamFileConsumer")
				.to("log:myLogger").id("streamLogger");
    }
}
