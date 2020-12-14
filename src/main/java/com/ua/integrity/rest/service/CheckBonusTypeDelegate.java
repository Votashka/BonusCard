package com.ua.integrity.rest.service;

import com.ua.integrity.model.ProcessVariables;
import com.ua.integrity.rest.instrument.InjClient;
import com.ua.integrity.rest.model.RequestBonusType;
import com.ua.integrity.rest.model.RequestCardNumber;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
public class CheckBonusTypeDelegate implements JavaDelegate {
    @Inject
    @InjClient
    private Client client;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String cardNumber = (String) delegateExecution.getVariable(ProcessVariables.CARD_NUMBER);
        RequestCardNumber requestCardNumber = new RequestCardNumber(cardNumber);
        Entity<RequestCardNumber> json = Entity.json(requestCardNumber);
        Response response = client.target("https://44766659-08dd-4c55-a28f-09c5f022073f.mock.pstmn.io/bonusType").request(MediaType.APPLICATION_JSON).post(json);
        String responseData = response.getEntity().toString();
        RequestBonusType requestBonusTypeAnswer = new RequestBonusType(responseData);
        String bonusType = requestBonusTypeAnswer.getBonusType();

        delegateExecution.setVariable(ProcessVariables.BONUS_TYPE, bonusType);
    }
}
