package com.ua.integrity.process;

import com.ua.integrity.model.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class RoundToIntegerAmountDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String amount = (String) delegateExecution.getVariable(ProcessVariables.AMOUNT);
        double amountD = new Double(amount);
        double bonusAmount = amountD - Math.floor(amountD);

        delegateExecution.setVariable(ProcessVariables.BONUS_AMOUNT, bonusAmount);
    }
}
