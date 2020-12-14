package com.ua.integrity.rest.endpoints;


import com.ua.integrity.model.ProcessVariables;
import com.ua.integrity.rest.model.StartProcessByMessageRequest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.cdi.annotation.ProcessEngineName;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("start")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StartProcessEndpoints {
    @Inject
    @ProcessEngineName("default")
    private ProcessEngine processEngine;

    @POST
    @Path("message")
    public Response startProcessInstanceByMessage (StartProcessByMessageRequest request){
        VariableMap variableMap = Variables
                .putValue(ProcessVariables.AMOUNT, request.getAmount())
                .putValue(ProcessVariables.CARD_NUMBER, request.getCardNumber());

        ProcessInstance processInstance;
        try {
            processInstance = processEngine.getRuntimeService()
                    .startProcessInstanceByMessage(ProcessVariables.START_PROCESS_BY_MESSAGE, variableMap);
        }catch (RuntimeException e){
            return  Response.serverError().entity(e.toString()).build();
        }
        return  Response.ok(processInstance.getProcessInstanceId()).build();

    }
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

}
