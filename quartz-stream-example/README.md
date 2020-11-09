# quartz-stream-example
## camel-spring-example
### what we are covering: quartz,stream, properties file, auto-startup, simple, osgi, log

Please build and follow instructions:

Please install camel-quartz, camel-spring, camel-stream, spring-dm features before running.

This will not run on karaf because `org.apache.camel.spring.handler.CamelNamespaceHandler` does not implement `org.springframework.beans.factory.xml.NamespaceHandler`.

As a work-around, if you try to do run it, it will fail at certain stage. Better to go for blueprint which is recommended.

Please run this in your local machine as in latest version of karaf, plain spring based examples can not be directly executed.

This example is created to show you tighly coupled versioning issues in karaf. If you have to use stream in your project please write code in blueprint.