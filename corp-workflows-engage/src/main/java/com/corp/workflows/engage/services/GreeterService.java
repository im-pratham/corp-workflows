package com.corp.workflows.engage.services;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.task.service.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GreeterService {

    private static final Logger logger = LoggerFactory.getLogger(GreeterService.class);

    public void greet(String greeter, String personToBeGreeted, DelegateExecution execution) {
        //You can access the execution context as you would do with with a JavaDelegate
        execution.setVariable("newVar", "newVarValue");
        greet(greeter, personToBeGreeted);
    }

    public void greet(String greeter, String personToBeGreeted) {
        logger.info(greeter + " says hello to " + personToBeGreeted);
    }
    
}