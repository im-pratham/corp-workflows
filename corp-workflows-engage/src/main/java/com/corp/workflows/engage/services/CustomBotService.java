package com.corp.workflows.engage.services;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowable.action.api.bot.BaseBotActionResult;
import com.flowable.action.api.bot.BotActionResult;
import com.flowable.action.api.bot.BotService;
import com.flowable.action.api.history.HistoricActionInstance;
import com.flowable.action.api.intents.Intent;
import com.flowable.action.api.repository.ActionDefinition;
import com.google.common.collect.ImmutableMap;

import org.springframework.stereotype.Service;

@Service
public class CustomBotService implements BotService {

    private final ObjectMapper objectMapper;

    public CustomBotService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String getKey() {
        return "custom-bot-service";
    }

    @Override
    public String getName() {
        return "Custom Bot Service";
    }

    @Override
    public String getDescription() {
        return "A custom Bot Service to showcase how to use the action engine.";
    }

    @Override
    public BotActionResult invokeBot(HistoricActionInstance actionInstance, ActionDefinition actionDefinition,
            Map<String, Object> payload) {

        String myPayloadVar = (String) payload.get("myPayloadVar");
        JsonNode botResult = objectMapper.valueToTree(ImmutableMap.of("myPayloadVarResult", myPayloadVar + "foo"));
        return new BaseBotActionResult(botResult, Intent.NOOP);

    }

}