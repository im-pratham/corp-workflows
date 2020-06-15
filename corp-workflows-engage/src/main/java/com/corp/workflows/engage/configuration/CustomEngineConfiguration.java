package com.corp.workflows.engage.configuration;

import com.flowable.spring.boot.EngineConfigurationConfigurer;
import com.google.common.collect.ImmutableList;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomEngineConfiguration {
    
    private final static Logger logger = LoggerFactory.getLogger(CustomEngineConfiguration.class);

	@Bean
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> customProcessEngineConfigurer() {
        return engineConfiguration -> {
            engineConfiguration.setEventListeners(ImmutableList.of(new CustomEngineListener()));
        };
    }

    private final class CustomEngineListener implements FlowableEventListener {

		@Override
		public void onEvent(FlowableEvent event) {
			logger.info("Event type '{}' - Class '{}'."  , event.getType(), event.getClass().getSimpleName());
		}

		@Override
		public boolean isFailOnException() {
			return false;
		}

		@Override
		public boolean isFireOnTransactionLifecycleEvent() {
			return false;
		}

		@Override
		public String getOnTransaction() {
			return null;
		}

	}
}