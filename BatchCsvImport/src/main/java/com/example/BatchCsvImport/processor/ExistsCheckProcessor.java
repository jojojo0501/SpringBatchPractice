package com.example.BatchCsvImport.processor;

import com.example.BatchCsvImport.domain.model.Employee;
import com.example.BatchCsvImport.repository.EmployeeJdbcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ExistsCheckProcessor")
@StepScope
@Slf4j
public class ExistsCheckProcessor implements ItemProcessor<Employee,Employee> {

    @Autowired
    private EmployeeJdbcRepository employeeJdbcRepository;

    @Override
    public Employee process(Employee employee) throws Exception {
        boolean exists = employeeJdbcRepository.exists(employee.getId());
        if(exists){
            log.info("Skip because it already exists:{}",employee);
            return null;
        }
        return employee;
    }
}
