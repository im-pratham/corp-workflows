package com.corp.workflows.engage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication(exclude = {FreeMarkerAutoConfiguration.class})
@EnableWebSocketMessageBroker
public class CorpWorkflowsEngageApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CorpWorkflowsEngageApplication.class, args);
    }
}
