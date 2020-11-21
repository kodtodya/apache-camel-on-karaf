package com.kodtodya.training.fuse.processor;

import java.util.List;

import com.kodtodya.training.fuse.beans.Employee;
import org.apache.camel.Exchange;
import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * Bean that generates and process employee.
 */
public class DataProcessor {

    private int counter=-1;

    public Employee generateEmployee(Exchange exchange) {
        Employee employee = new Employee();
        String content = exchange.getIn().getBody(String.class);
        employee.setId( Integer.toString(++counter));
        employee.setName(content.split(",")[0]);
        employee.setCity(content.split(",")[1]);
        employee.setDept(content.split(",")[2]);
        return employee;
    }

/*    public String processEmployee(Employee employee) {
        return "Processed employee id: " + employee.getId() + " | name: " + employee.getName()
                + " | City: " + employee.getCity() + " | Dept: " + employee.getDept();
    }*/

    public String tableRowToResponse(List<LinkedCaseInsensitiveMap<String>> employeeEntries) {

        StringBuilder response = new StringBuilder();
        for (LinkedCaseInsensitiveMap<String> map : employeeEntries) {
            Employee employee = new Employee();
            employee.setId(map.get("id"));
            employee.setName(map.get("name"));
            employee.setCity(map.get("city"));
            employee.setDept(map.get("dept"));
            response.append(employee).append("\n");
        }
        return response.toString();
    }
}
