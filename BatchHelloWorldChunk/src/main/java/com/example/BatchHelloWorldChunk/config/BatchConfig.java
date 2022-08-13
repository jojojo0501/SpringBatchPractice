package com.example.BatchHelloWorldChunk.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemReader<String> reader;

    @Autowired
    private ItemProcessor<String,String> processor;

    @Autowired
    private ItemWriter<String> writer;

    @Autowired
    private JobExecutionListener jobExecutionListener;

    @Autowired
    private StepExecutionListener stepExecutionListener;

    @Bean
    public Step chunkStep(){
        return stepBuilderFactory.get("HelloChunkStep")
                .<String,String>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(stepExecutionListener)
                .build();
    }

    @Bean
    public Job chunkJob() throws Exception {
        return jobBuilderFactory.get("HelloWorldChunkJob")
                .incrementer(new RunIdIncrementer())
                .start(chunkStep())
                .listener(jobExecutionListener)
                .build();
    }
}
