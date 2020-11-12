package com.kodtodya.training.fuse.routes;

import com.kodtodya.training.fuse.strategy.DepartmentEmployeeStrategy;
import org.apache.camel.builder.RouteBuilder;

public class SplitterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //in only
        from("timer:myTimer?period=1000&repeatCount=1")
                .process("employeeProcessor")
                // async communication
                .to("seda:processDept");

        // department object
        // spillter & aggregator
        from("seda:processDept")
                // body type = department.getEmployees()
                // here we will get a list of objects(Employees)
                // 10k records
                .split(simple("\n"))
                .split(simple("${body.getEmployees}"))
                // 1k records
                //you did certain processing
                .parallelProcessing()
                .aggregationStrategy(new DepartmentEmployeeStrategy())
                .to("direct-vm:processEmployee").end();

        from("direct-vm:processEmployee")
                .choice()
                // body type = Employee
                // deppt = Fiannce -> sending to processFinanceDept
                .when(simple("${body.getDepartment} == 'Finance'"))
                    .to("bean:employeeService?method=processFinanceDept")
                .when(simple("${body.getDepartment} == 'IT'"))
                    .to("bean:employeeService?method=processITDept");
    }
}