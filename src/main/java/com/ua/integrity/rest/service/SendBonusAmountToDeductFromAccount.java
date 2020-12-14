package com.ua.integrity.rest.service;

import com.ua.integrity.model.ProcessVariables;
import com.ua.integrity.rest.instrument.InjClient;
import com.ua.integrity.rest.model.RequestDeductFromAccount;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;


@Named
public class SendBonusAmountToDeductFromAccount implements JavaDelegate  {
    @Inject
    @InjClient
    private Client client;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String cardNumber = (String) delegateExecution.getVariable(ProcessVariables.CARD_NUMBER);
        String bonusAmount = (String) delegateExecution.getVariable(ProcessVariables.BONUS_AMOUNT);
        String deductAmount = (String) delegateExecution.getVariable(ProcessVariables.BONUS_AMOUNT);

        RequestDeductFromAccount requestDeductFromAccount = new RequestDeductFromAccount(cardNumber, bonusAmount, deductAmount);
        Entity<RequestDeductFromAccount> json = Entity.json(requestDeductFromAccount);
        client.target("https://d198a473-732d-4750-b114-5d9f2e86ca11.mock.pstmn.io/deduct_from_card").request(MediaType.APPLICATION_JSON).post(json);

    }
}
