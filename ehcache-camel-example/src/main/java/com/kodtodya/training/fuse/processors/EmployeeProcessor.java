package com.kodtodya.training.fuse.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.ehcache.EhcacheConstants;

import java.util.HashMap;
import java.util.Map;


public class EmployeeProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("id", "kodtodya");
        inputMap.put("company", "kodtodya-talks");
        inputMap.put("city", "Pune");
        exchange.getIn().setBody(inputMap);
        exchange.getIn().setHeader("CamelEhcacheAction", EhcacheConstants.ACTION_PUT_ALL);
    }
}