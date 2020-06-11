package com.corp.workflows.engage.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.flowable.platform.rest.service.api.util.ResourceUtils;
import com.flowable.platform.service.index.WorkIndexService;
import com.flowable.platform.service.task.TaskSearchRepresentation;
import com.flowable.platform.service.task.TasksIndexQueryRequest;
import com.google.common.collect.ImmutableMap;

import org.apache.commons.lang3.ObjectUtils;
import org.flowable.common.rest.api.DataResponse;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corp-workflows-api")
public class CorpWorkflowsController {

    private final RepositoryService repositoryService;
    private final WorkIndexService workIndexService;
    private final TaskService taskService;
    private final HistoryService historyService;

    public CorpWorkflowsController(RepositoryService repositoryService, WorkIndexService workIndexService,
            TaskService taskService, HistoryService historyService) {
        this.repositoryService = repositoryService;
        this.workIndexService = workIndexService;
        this.taskService = taskService;
        this.historyService = historyService;
    }

    @GetMapping("/definition/{processModelKey}")
    public String getProcessDefinition(@PathVariable String processModelKey) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processModelKey).latestVersion().singleResult();
        return processDefinition.getId();

    }

    // Example request:
    // http://localhost:8090/flowable-engage/corp-workflows-api/all-tasks?scopeType=bpmn&completed=true
    @GetMapping("/all-tasks")
    public DataResponse<TaskSearchRepresentation> getAllTasks(@ModelAttribute TasksIndexQueryRequest request) {
        return ResourceUtils.setDataResponse(this.workIndexService.findTasksWithQuery(request));
    }

    @GetMapping("/runtime-tasks")
    public List<Map<String,String>> getRuntimeTasks() {
        return taskService.createTaskQuery().list().stream().map(t -> ImmutableMap.of("id",t.getId(),"name",t.getName(),"scopeType", ObjectUtils.firstNonNull(t.getScopeType(),"bpmn"))).collect(Collectors.toList());
    }

    @GetMapping("history-tasks")
    public List<Map<String,String>> getHistoryTasks() {
        return historyService.createHistoricTaskInstanceQuery().list().stream().map(t -> ImmutableMap.of("id",t.getId(),"name",t.getName(),"scopeType",ObjectUtils.firstNonNull(t.getScopeType(),"bpmn"))).collect(Collectors.toList());
    }

}