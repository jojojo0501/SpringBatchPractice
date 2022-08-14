package com.example.BatchHelloWorldTasklet.property;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:property/sample.properties")
@Getter
@ToString
public class SampleProperty {
    @Value("${sample.property}")
    private String sampleProperty;
}
