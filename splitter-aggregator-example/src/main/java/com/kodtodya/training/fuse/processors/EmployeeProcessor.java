package com.kodtodya.training.fuse.processors;

import com.kodtodya.training.fuse.beans.Department;
import com.kodtodya.training.fuse.beans.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;


public class EmployeeProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("1", "Avadhut", "Finance"));
        employees.add(new Employee("2", "Kodtodya", "IT"));
        employees.add(new Employee("2", "Kodtodya1", "IT"));

        Department dept = new Department();
        dept.setEmployees(employees);

        exchange.getIn().setBody(dept);
    }
}