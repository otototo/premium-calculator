package com.pm.homework.policy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

class RiskTypeTest {

    @ParameterizedTest
    @EnumSource(RiskType.class)
    void whenFireInsuranceIsZeroThenUseDefault(RiskType riskType) {
        Assertions.assertEquals(riskType.getDefaultCoefficient(), riskType.getCoefficient(BigDecimal.ZERO));
    }

    @ParameterizedTest
    @EnumSource(RiskType.class)
    void whenFireInsuranceIsOnBoundaryThenUseDefault(RiskType riskType) {
        BigDecimal actualCoefficient = riskType.getCoefficient(riskType.getInsuranceBoundary());
        Assertions.assertEquals(riskType.getDefaultCoefficient(), actualCoefficient);
    }

    @ParameterizedTest
    @EnumSource(RiskType.class)
    void whenFireInsuranceIsOverBoundaryThenUseDefault(RiskType riskType) {
        BigDecimal actualCoefficient = riskType.getCoefficient(riskType.getInsuranceBoundary().add(BigDecimal.ONE));
        Assertions.assertEquals(riskType.getLargerSumCoefficient(), actualCoefficient);
    }
}