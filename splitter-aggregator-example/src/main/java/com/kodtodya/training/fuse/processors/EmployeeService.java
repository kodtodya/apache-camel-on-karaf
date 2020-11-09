package com.kodtodya.training.fuse.processors;

import com.kodtodya.training.fuse.beans.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	public Employee processITDept(Employee employee) throws InterruptedException {

		this.drawLine();
		logger.info("handling employee department:" + employee);
		employee.setSalary(10000);
		logger.info("IT dept employee processed");

		return employee;
	}

	public Employee processFinanceDept(Employee employee) throws InterruptedException {

		this.drawLine();
		logger.info("handling employee department:" + employee);
		employee.setSalary(5000);

		logger.info("Finance dept employee processed");

		return employee;
	}

	private void drawLine() {
		StringBuilder line = new StringBuilder("-");
		for (int i=0; i<5; i++) {
			line.append(line);
		}
		logger.info(line.toString());
	}
}