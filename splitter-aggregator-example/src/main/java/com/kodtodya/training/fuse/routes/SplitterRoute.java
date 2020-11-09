package com.kodtodya.training.fuse.routes;

import com.kodtodya.training.fuse.strategy.DepartmentEmployeeStrategy;
import org.apache.camel.builder.RouteBuilder;

public class SplitterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:myTimer?period=1000&repeatCount=1")
                .process("employeeProcessor")
                .to("seda:processDept");

        from("seda:processDept")
                .split(simple("${body.getEmployees}"))
                .aggregationStrategy(new DepartmentEmployeeStrategy())
                .to("direct-vm:processEmployee").end();

        from("direct-vm:processEmployee").choice()
                .when(simple("${body.getDepartment} == 'Finance'"))
                    .to("bean:employeeService?method=processFinanceDept")
                .when(simple("${body.getDepartment} == 'IT'"))
                    .to("bean:employeeService?method=processITDept");
    }
}