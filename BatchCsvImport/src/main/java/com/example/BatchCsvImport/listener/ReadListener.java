package com.example.BatchCsvImport.listener;

import com.example.BatchCsvImport.domain.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReadListener implements ItemReadListener<Employee> {
    @Override
    public void beforeRead() {
        // Do nothing
    }

    @Override
    public void afterRead(Employee item) {
        log.debug("After Read:{}",item);
    }

    @Override
    public void onReadError(Exception e) {
        log.error("ReadError:errorMessage",e.getMessage(),e);
    }
}
