package com.ua.integrity.process;

import com.ua.integrity.model.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings
class GetFixedPercentDelegateTest {

    @Mock
    DelegateExecution execution;

    GetFixedPercentDelegate getFixedPercentDelegate;

    @Captor
    ArgumentCaptor<Double> bonusAmountCaptor;

    @BeforeEach
    void setUp() {
        getFixedPercentDelegate = new GetFixedPercentDelegate();

    }

    @ParameterizedTest
    @CsvSource(value = {
            "100:0.05", "50:0.02", "10213:5.10", "10595.65:5.29"
    }, delimiter = ':')
    void execute(String inputAmount, String expectedBonusAmount) throws Exception {
        when(execution.getVariable(eq(ProcessVariables.AMOUNT))).thenReturn(inputAmount);
        getFixedPercentDelegate.execute(execution);
        verify(execution).setVariable(eq(ProcessVariables.BONUS_AMOUNT), bonusAmountCaptor.capture());
        String result = String.valueOf(bonusAmountCaptor.getValue());
        assertEquals(expectedBonusAmount, result);
    }
}