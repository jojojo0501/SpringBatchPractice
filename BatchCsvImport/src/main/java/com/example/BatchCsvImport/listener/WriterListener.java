package com.example.BatchCsvImport.listener;

import com.example.BatchCsvImport.domain.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WriterListener implements ItemWriteListener<Employee> {

    @Override
    public void beforeWrite(List<? extends Employee> list) {
        // Do nothing
    }

    @Override
    public void afterWrite(List<? extends Employee> items) {
        log.debug("AfterWrite: count={}",items.size());
    }

    @Override
    public void onWriteError(Exception e, List<? extends Employee> items) {
        log.error("WriteError:errorMessage={}",e.getMessage(),e);
    }
}
