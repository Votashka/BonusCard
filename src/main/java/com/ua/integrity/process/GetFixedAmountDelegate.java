package com.ua.integrity.process;

import com.ua.integrity.model.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class GetFixedAmountDelegate implements JavaDelegate {

    // TODO: 18.01.2021 а почему при фиксированной ставке бонуса мы от покупки отнимаем фиксированный размер?
    // обычно если размер зафиксирован - то снимается фиксированная сумма, например 0.5 грн как в товем примере, зачем отнимать?
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String amount = (String) delegateExecution.getVariable(ProcessVariables.AMOUNT);
        double amountD = new Double(amount);
        double fixedAmount = 0.5;
        double bonusAmount = amountD-fixedAmount;

        delegateExecution.setVariable(ProcessVariables.BONUS_AMOUNT,bonusAmount);
    }
}
