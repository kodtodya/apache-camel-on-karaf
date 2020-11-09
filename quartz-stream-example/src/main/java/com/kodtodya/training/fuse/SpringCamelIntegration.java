package com.kodtodya.training.fuse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCamelIntegration {

	public static void main(String[] args) {
		ApplicationContext springContext = new ClassPathXmlApplicationContext("classpath:META-INF/spring/camel-context.xml");
	}
}