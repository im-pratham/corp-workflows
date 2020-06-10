package com.corp.workflows.engage.controllers;

import com.flowable.platform.rest.service.api.util.ResourceUtils;
import com.flowable.platform.service.index.WorkIndexService;
import com.flowable.platform.service.task.PlatformTaskService;
import com.flowable.platform.service.task.TaskSearchRepresentation;
import com.flowable.platform.service.task.TasksIndexQueryRequest;

import org.flowable.cmmn.api.CmmnTaskService;
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
    
    public CorpWorkflowsController(RepositoryService repositoryService, WorkIndexService workIndexService) {
        this.repositoryService = repositoryService;
        this.workIndexService = workIndexService;
    }

    @GetMapping("/definition/{processModelKey}")
    public String getProcessDefinition(@PathVariable String processModelKey) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processModelKey).latestVersion().singleResult();
        return processDefinition.getId();

    }

    // Example request: http://localhost:8090/flowable-engage/corp-workflows-api/all-tasks?scopeType=bpmn&completed=true
    @GetMapping("/all-tasks")
    public DataResponse<TaskSearchRepresentation> getAllTasks(@ModelAttribute TasksIndexQueryRequest request) {
        return ResourceUtils.setDataResponse(this.workIndexService.findTasksWithQuery(request));
    }



}