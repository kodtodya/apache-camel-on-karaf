package com.kodtodya.training.fuse.strategy;

import com.kodtodya.training.fuse.beans.Department;
import com.kodtodya.training.fuse.beans.Employee;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DepartmentEmployeeStrategy implements AggregationStrategy {

    Logger logger = LoggerFactory.getLogger(DepartmentEmployeeStrategy.class);
    
    public Exchange aggregate(Exchange oldEmployeeExchange, Exchange newEmployeeExchange) {
        
           if (oldEmployeeExchange == null) {
               Employee newEmployee= newEmployeeExchange.getIn().getBody(Employee.class);
               logger.info("Aggregate first employee: " + newEmployee);
               
               Department department = new Department();
               List<Employee> employees = new ArrayList<Employee>();
   
               employees.add(newEmployee);
               department.setEmployees(employees);
               department.setTotalSalary(newEmployee.getSalary());
               
               newEmployeeExchange.getIn().setBody(department);
               
                return newEmployeeExchange;
            }
           
            Department department = oldEmployeeExchange.getIn().getBody(Department.class);
            Employee newEmployee= newEmployeeExchange.getIn().getBody(Employee.class);

            logger.info("Aggregate old employees: " + department);
            logger.info("Aggregate new department: " + newEmployee);
            
            department.getEmployees().add(newEmployee);
           
            double totalSalary = department.getTotalSalary() + newEmployee.getSalary();
            department.setTotalSalary(totalSalary);

            return oldEmployeeExchange;
    }

}