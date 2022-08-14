package com.example.BatchHelloWorldTasklet.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("Hello Tasklet")
@StepScope
@Slf4j
public class HelloTasklet implements Tasklet {

    @Value("#{JobParameters['require1']}")
    private String require1;

    @Value("#{JobParameters['option1']}")
    private String option1;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Hello World!!");

        ExecutionContext jobContext = contribution.getStepExecution()
                .getJobExecution().getExecutionContext();
        jobContext.put("jobKey","jobValue");

        ExecutionContext stepContext = contribution.getStepExecution().getExecutionContext();
        stepContext.put("stepKey","stepValue");

        log.info("require1={}",require1);
        log.info("option1={}",option1);

        return RepeatStatus.FINISHED;
    }
}
