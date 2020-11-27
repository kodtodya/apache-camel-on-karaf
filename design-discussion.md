## discussion for service design

-- psuedo code | not to measure from errors
```
// phase-1
        from("jms:test-q?concurrentConsumers=5").routeId("jms-reader-route")
                .recipientList("seda:mySedaDBComponent,kafka:just-received?parallelProcessing=true");

        //pahse-2
        from("kafka:just-received?concurrentStream=5").routeId("kafka-phase-1-route")
                .unmarshal().jacksonxml()//.validate().body()
                .marshal().json(true)
                .recipientList("seda:mySedaDBComponent,kafka:transformed-payment?parallelProcessing=true");

        // phase -3
        from("kafka:transformed-payment?concurrentStream=5").routeId("kafka-phase-1-route")
                .setHeader("test-header", simple("${date:now:timestamp:ddMMyyyy}"))
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.HTTP_URI, constant("https://client-service.com/my-base-service/actual-service"))
                .to("https://certain-service")
                .recipientList("seda:mySedaDBComponent,kafka:rest-response?parallelProcessing=true");

        // last phase
        from("kafka:rest-response?concurrentStream=5").routeId("kafka-phase-1-route")
                .to("jms:outbound-queue");


        // db routes -> async db insertions
        from("seda:mySedaDBComponent?concurrentConsumers=5").routeId("seda-db-writer-route")
                .setHeader("", constant())
                .to("sql-stored:classpath:sql/myprocedure.sql")
                .id("sql-stored-proc");
```

### dynamic conditional statements along with exchange properties and headers

```
                from("jms:my-q").routeId("jms-reader-route")
                //sent to db - asyn
                // processing -> conditions
                .setHeader("is-everything-ok", constant("true"))
                .setProperty("is-everything-ok", constant("true"))
                //processing
                .to("")
                //processing
                .to("kafka:myKafkaComponent").id("direct-producer");

        from("kafka:myKafkaComponent").routeId("jms-reader-route")
                .choice()
                .when(simple("${exchangeProperties.is-everything-ok} == 'true'"))
                    .to("validator:classpath:xsd/my-pacs008.xsd")
                .end()
                .to("kafka:myKafkaComponent").id("direct-producer");

```
