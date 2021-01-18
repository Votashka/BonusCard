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
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
class RoundToIntegerAmountDelegateTest {

    @Mock
    DelegateExecution execution;

    RoundToIntegerAmountDelegate roundToIntegerAmountDelegate;

    @Captor
    ArgumentCaptor<Double> bonusAmountCaptor;

    @BeforeEach
    void setUp() {
        roundToIntegerAmountDelegate = new RoundToIntegerAmountDelegate();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "100:0", "1.20:0.80", "0.26:0.64", "1896.98:0.02"
    }, delimiter = ':')
    void test_execute(String inputAmount, String expectedBonusAmount) throws Exception {
        when(execution.getVariable(eq(ProcessVariables.AMOUNT))).thenReturn(inputAmount);
        roundToIntegerAmountDelegate.execute(execution);
        verify(execution).setVariable(eq(ProcessVariables.BONUS_AMOUNT), bonusAmountCaptor.capture());
        String result = String.valueOf(bonusAmountCaptor.getValue());
        assertEquals(expectedBonusAmount, result);
    }
}