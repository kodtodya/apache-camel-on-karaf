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
                // Please read documentation for transaction here:
                // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/TransactionDefinition.html

                // PROPAGATION_REQUIRED : Support a current transaction; create a new one if none exists.
                .transacted("PROPAGATION_REQUIRED")
                // set no. of threads as per requirement
                .threads(5, 25,"postgres-data-fetch-thread")
                .to("sql:{{sql.fetch.all.employees}}?consumer.onConsume={{sql.updateEmployee}}")
                .to("bean:dataProcessor?method=tableRowToResponse")
                .log("${body}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

    }
}
