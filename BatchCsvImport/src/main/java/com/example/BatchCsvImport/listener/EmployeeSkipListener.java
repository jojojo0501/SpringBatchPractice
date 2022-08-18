package com.example.BatchCsvImport.listener;

import com.example.BatchCsvImport.domain.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class EmployeeSkipListener implements SkipListener<Employee,Employee> {

    @Override
    public void onSkipInRead(Throwable throwable) {
        log.warn("Skip by Read Error:errorMessage={}",throwable.getMessage());
    }

    @Override
    public void onSkipInWrite(Employee employee, Throwable throwable) {
        log.warn("Skip by Write Error:errorMessage={}",throwable.getMessage());
    }

    @Override
    public void onSkipInProcess(Employee employee, Throwable throwable) {
        log.warn("Skip by Process Error:errorMessage={}",throwable.getMessage());
    }
}
