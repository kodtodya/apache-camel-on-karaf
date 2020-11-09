package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;

public class EhcacheRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:foo?period=1000&repeatCount=1")
                .log("Timer triggered.. we will insert data into ehcache..")
                .process("employeeProcessor")
                .bean("myCacheMgr", "populateCache")
                .log("Data sent to EhCache.. now trying to read it..")
                .bean("myCacheMgr", "dumpCache")
                .log("dumpCache");
    }
}