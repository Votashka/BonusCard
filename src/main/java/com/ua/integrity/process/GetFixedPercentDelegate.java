package com.ua.integrity.process;

import com.ua.integrity.model.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class GetFixedPercentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String amount = (String) delegateExecution.getVariable(ProcessVariables.AMOUNT);
        double amountD = new Double(amount);
        double fixedPercent = 0.05;
        double bonusAmount = (double) Math.round(fixedPercent*amountD/100 * 10000d) / 10000d;

        delegateExecution.setVariable(ProcessVariables.BONUS_AMOUNT,bonusAmount);
    }
}
