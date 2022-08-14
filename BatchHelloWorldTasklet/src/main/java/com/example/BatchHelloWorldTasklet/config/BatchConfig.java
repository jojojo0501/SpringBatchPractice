package com.example.BatchHelloWorldTasklet.config;

import com.example.BatchHelloWorldTasklet.validator.OptionValidator;
import com.example.BatchHelloWorldTasklet.validator.RequireValidator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("Hello Tasklet")
    private Tasklet helloTasklet;

    @Autowired
    @Qualifier("Hello Tasklet2")
    private Tasklet helloTasklet2;

    @Bean
    public JobParametersValidator defaultValidator(){
        DefaultJobParametersValidator validator =
                new DefaultJobParametersValidator();
        String [] requiredKeys = new String[]{"run.id","require1"};
        validator.setRequiredKeys(requiredKeys);
        String [] optionKeys = new String[]{"option1"};
        validator.setOptionalKeys(optionKeys);
        validator.afterPropertiesSet();
        return validator;
    }

    @Bean
    public JobParametersValidator compositeValidator(){
        List<JobParametersValidator> validators = new ArrayList<>();
        validators.add(defaultValidator());
        validators.add(new RequireValidator());
        validators.add(new OptionValidator());

        CompositeJobParametersValidator compositeJobParametersValidator =
                new CompositeJobParametersValidator();
        compositeJobParametersValidator.setValidators(validators);
        return compositeJobParametersValidator;
    }

    @Bean
    public Step taskletStep1(){
        return stepBuilderFactory.get("HelloTaskletStep1").tasklet(helloTasklet).build();
    }

    @Bean
    public Step taskletStep2(){
        return stepBuilderFactory.get("HelloTaskletStep2").tasklet(helloTasklet2).build();
    }

    @Bean
    public Job taskletJob() throws Exception {
        return jobBuilderFactory.get("HelloWorldTaskletJob")
                .incrementer(new RunIdIncrementer()).start(taskletStep1()).next(taskletStep2())
                .validator(compositeValidator()).build();
    }
}
