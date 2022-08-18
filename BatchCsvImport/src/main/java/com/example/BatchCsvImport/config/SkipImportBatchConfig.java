package com.example.BatchCsvImport.config;

import com.example.BatchCsvImport.domain.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkipImportBatchConfig extends BaseConfig {

    @Autowired
    private SkipListener<Employee,Employee> employeeSkipListener;

    @Autowired JdbcImportBatchConfig jdbcImportBatchConfig;

    @Bean
    public Step csvImportSkipStep(){
        return this.stepBuilderFactory.get("CsvImportSkipStep")
                .<Employee,Employee>chunk(10)
                .reader(csvReader()).listener(this.readListener)
                .processor(genderConvertProcessor).listener(this.processListener)
                .writer(jdbcImportBatchConfig.jdbcWriter())
                .faultTolerant()
                .skipLimit(Integer.MAX_VALUE)
                .skip(RuntimeException.class)
                .listener(this.employeeSkipListener)
                .build();
    }

    @Bean
    public Job csvImportSkipJob(){
        return this.jobBuilderFactory.get("CsvImportSkipJob")
                .incrementer(new RunIdIncrementer())
                .start(csvImportSkipStep())
                .build();
    }
}
