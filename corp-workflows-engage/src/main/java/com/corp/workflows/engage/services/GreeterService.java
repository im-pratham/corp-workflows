package com.corp.workflows.engage.services;

import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreeterService implements JavaDelegate{

    private static final Logger logger = LoggerFactory.getLogger(GreeterService.class);

    public void greet(String greeter, String personToBeGreeted, DelegateExecution execution) {
        //You can access the execution context as you would do with with a JavaDelegate
        if ("Antonio".equals(greeter)) {
            throw new BpmnError("antonioNotAllowedToGreet");
        }
        execution.setVariable("newVar", "newVarValue");
        greet(greeter, personToBeGreeted);
    }

    public void greet(String greeter, String personToBeGreeted) {
        logger.info(greeter + " says hello to " + personToBeGreeted);
    }

    @Override
    public void execute(DelegateExecution execution) {
        String greeter = execution.getVariable("greeter", String.class);
        String personToBeGreeted = execution.getVariable("greeter", String.class);
        if ("Jose Luis".equals(greeter)) {
            throw new FlowableIllegalArgumentException("Jose Luis is not allowed to greet.");
        }
        greet(greeter,personToBeGreeted);
    }
    
}