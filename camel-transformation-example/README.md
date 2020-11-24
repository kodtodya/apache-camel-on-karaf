# camel-transformation-example
## camel-blueprint-example
### We are covering below topics in this example:
- jackson xml
- jackson json
- blueprint
- file producer, consumer
- exception handling
- validator for xml validation against xsd

Please build and follow instructions:

** first change the file location in `application.properties` so that your file will be written to appropriate location.

** Build this application with `mvn clean install -DskipTests`

Please invoke blow commands in `Karaf` server sequentially to have smooth execution:

** PLEASE DON'T CHANGE THE SEQUENCE OF COMMANDS.
```
    feature:install scr aries-blueprint

    feature:repo-add camel 3.4.0

    feature:install camel-blueprint camel-jacksonxml

    bundle:install -s wrap:mvn:com.kodtodya.training.fuse/camel-transformation-example/1.0-SNAPSHOT
```

** After starting bundle, please check the appropriate configured file location for newly generated json file