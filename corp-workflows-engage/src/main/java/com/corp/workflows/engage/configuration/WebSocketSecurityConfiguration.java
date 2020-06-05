package com.corp.workflows.engage.configuration;

import com.flowable.autoconfigure.security.FlowablePlatformSecurityProperties;
import com.flowable.engage.autoconfigure.websocket.WebSocketMessageBrokerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    protected final WebSocketMessageBrokerProperties brokerProperties;
    protected final FlowablePlatformSecurityProperties flowableSecurityProperties;

    public WebSocketSecurityConfiguration(WebSocketMessageBrokerProperties brokerProperties,
                                          FlowablePlatformSecurityProperties flowableSecurityProperties) {
        this.brokerProperties = brokerProperties;
        this.flowableSecurityProperties = flowableSecurityProperties;
    }

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        // In case there are destination prefixes deny all of them as they are forwarded to the broker
        String[] destinationPrefixes = brokerProperties.getDestinationPrefixes();
        if (destinationPrefixes != null) {
            for (String destinationPrefix : destinationPrefixes) {
                messages.simpMessageDestMatchers(destinationPrefix + "/**").denyAll();
            }
        }
        messages.anyMessage().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        // Determines if a CSRF token is required for connecting. This protects against remote
        // sites from connecting to the application and being able to read/write data over the
        // connection. The default is false (the token is required).
        // If CSRF is not enabled then same origin is disabled
        // if CSRF is enabled then same origin is enabled (the token is required)
        return !flowableSecurityProperties.getRest().getCsrf().isEnabled();
    }
}
