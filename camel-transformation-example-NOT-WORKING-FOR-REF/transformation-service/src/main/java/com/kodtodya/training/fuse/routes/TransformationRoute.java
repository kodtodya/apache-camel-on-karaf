package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;

public class TransformationRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

/*        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");*/

        // read messages from jms & send to file
        from("file:{{input.file.location}}")
                .convertBodyTo(String.class)
                // transform the csv file into csv-POJOs
                .to("bean:pacs008Mapper?method=mapCsvToPojos")

                .to("direct:transformationDirect");

        from("direct:transformationDirect")
                // transform the csv-POJOs into XML
                .to("bean:pacs008Mapper?method=mapCsvPojosToXml")
                .to("file:{{output.file.location}}?fileName=${header.output-filename}");
    }
}