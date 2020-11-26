package com.kodtodya.training.fuse.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class RouteTests extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MyRestRoute();
    }

    @Test
    public void testReturnsProperty() throws Exception {
        // assert that the CamelContext starts up correctly
        assertTrue(context.getStatus().isStarted());
    }

    @Test
    public void testRestCall(){
        String response =   template.requestBody("direct:restCall","",String.class);
        System.out.println("response : " + response);
        assertNotNull(response);
    }
}