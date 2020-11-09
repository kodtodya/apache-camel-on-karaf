# rest-camel-example
## camel-blueprint-example
### We are covering below topics in this example:
- jetty
- rest
- blueprint
- bean

Please build and follow instructions:

if `blueprint` & `jetty` is not installed; please install:
```
    feature:install camel-blueprint
    feature:install camel-jetty    

```

install bundle to karaf container
```
    bundle:install -s mvn:com.kodtodya.training.fuse/ehcache-camel-example/1.0-SNAPSHOT
```

Run as:
```
    http://localhost:8888/checkprime/10
```
