package com.example.BatchHelloWorldTasklet.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("Hello Tasklet2")
@StepScope
@Slf4j
public class HelloTasklet2 implements Tasklet {

    @Value("#{JobExecutionContext['jobKey']}")
    private String jobValue;

    @Value("#{StepExecutionContext['stepKey']}")
    private String stepValue;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Hello World2");
        log.info("jobKey={}",jobValue);
        log.info("stepKey={}",stepValue);
        return RepeatStatus.FINISHED;
    }
}
