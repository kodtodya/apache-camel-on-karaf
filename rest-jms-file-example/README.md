# rest-jms-file-example
## camel-blueprint-example
### We are covering below topics in this example:
- netty-http
- rest
- blueprint
- jms producer
- jms consumer
- file producer
- throttler
- exception handling

Please build and follow instructions:

** first change the file producer location in `JmsToFileRoute` so that your file will be written to appropriate location.

** Build this application with `mvn clean install -DskipTests`

Please invoke blow commands in `Karaf` server sequentially to have smooth execution:

** PLEASE DON'T CHANGE THE SEQUENCE OF COMMANDS.
```
    feature:install scr aries-blueprint

    feature:repo-add mvn:org.ops4j.pax.jms/pax-jms-features/1.0.0/xml/features
    
    feature:repo-add mvn:org.apache.activemq/artemis-features/2.5.0/xml/features
    
    feature:repo-add mvn:org.ops4j.pax.jms/pax-jms-features/1.0.0/xml/features

    feature:repo-add camel 3.4.0

    feature:install camel-blueprint

    feature:install camel-quartz camel-activemq camel-jms

    feature:install transaction jms pax-jms-config pax-jms-artemis

    bundle:install -s mvn:javax.jms/javax.jms-api/2.0.1
    
    bundle:install -s wrap:mvn:org.apache.activemq/artemis-jms-client/2.2.0

    bundle:install -s wrap:mvn:org.apache.activemq/artemis-core-client/2.2.0

    bundle:install -s wrap:mvn:org.apache.activemq/artemis-commons/2.2.0

    bundle:install -s wrap:mvn:org.apache.geronimo.specs/geronimo-jms_1.1_spec/1.1

    feature:install http camel-netty camel-netty-http

    bundle:install -s mvn:com.kodtodya.training.fuse/rest-jms-file-example/1.0-SNAPSHOT
```

Now, start the activemq-artemis broker on localhost:61616

** After deployment of bundle, please invoke below rest service with plain text sample body:

REST -> POST -> http://localhost:8888/fuse-demo/sendMessage

