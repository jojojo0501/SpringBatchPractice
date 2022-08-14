package com.example.BatchHelloWorldTasklet.validator;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class RequireValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String key = "require1";
        String require1 = parameters.getString(key);

        if(StringUtils.isEmpty(require1)){
            String errorMsg = "Not Enterd:" + key;
            throw  new JobParametersInvalidException(errorMsg);
        }
    }
}
