package com.kodtodya.training.fuse.routes;

import com.kodtodya.training.fuse.strategy.LineAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;

public class SplitterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //in only
        from("file:/home/kodtodya/Downloads/_test?noop=true&initialDelay=2000&bufferSize=512")
                .split(bodyAs(String.class).tokenize("\n"))

                .aggregationStrategy(new LineAggregationStrategy())
                .log("data before sending to direct:processLine --> ${body}")
                .to("direct:processLine").end();

        from("direct:processLine")
                .log("Body in logger is: ${body}");
    }
}