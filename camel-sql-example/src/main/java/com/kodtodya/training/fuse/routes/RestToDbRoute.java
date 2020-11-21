package com.kodtodya.training.fuse.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestToDbRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        restConfiguration()
                .component("netty-http")
                .host("0.0.0.0")
                .port(7777)
                .bindingMode(RestBindingMode.auto);

        // use the rest DSL to define the rest services
        rest("/fuse-demo")
                .post("/db-insert")
                    .consumes("application/text")
                    .id("rest-db-insert-service")
                    .to("direct:insertIntoEmployeesTable")
                .get("/db-fetch")
                    .consumes("application/text")
                    .id("rest-db-insert-service")
                    .to("direct:fetchFromEmployeesTable");

        // send message to jms server
        from("direct:insertIntoEmployeesTable")
                .convertBodyTo(String.class)
                .to("bean:dataProcessor?method=generateEmployee")
                .throttle(10).timePeriodMillis(10000)
                .to("sql:{{sql.insertEmployee}}")
                .setBody(simple("Inserted new Employee ${body.id}"))
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

        from("direct:fetchFromEmployeesTable")
                .to("sql:{{sql.fetch.all.employees}}?consumer.onConsume={{sql.updateEmployee}}")
                .to("bean:dataProcessor?method=tableRowToResponse")
                .log("${body}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

    }
}
