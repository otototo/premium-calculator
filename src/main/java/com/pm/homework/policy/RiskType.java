package com.pm.homework.policy;

import java.math.BigDecimal;

public enum RiskType {
    FIRE(BigDecimal.valueOf(0.014), BigDecimal.valueOf(0.024), BigDecimal.valueOf(100)),
    THEFT(BigDecimal.valueOf(0.11), BigDecimal.valueOf(0.05), BigDecimal.valueOf(15));

    private final BigDecimal defaultCoefficient;
    private final BigDecimal largerSumCoefficient;
    private final BigDecimal insuranceBoundary;

    RiskType(BigDecimal defaultCoefficient, BigDecimal largerSumCoefficient, BigDecimal insuranceBoundary) {
        this.defaultCoefficient = defaultCoefficient;
        this.largerSumCoefficient = largerSumCoefficient;
        this.insuranceBoundary = insuranceBoundary;
    }

    public BigDecimal getCoefficient(BigDecimal sumInsured) {
        return sumInsured.compareTo(insuranceBoundary) > 0 ?
                largerSumCoefficient : defaultCoefficient;
    }

    BigDecimal getDefaultCoefficient() {
        return defaultCoefficient;
    }

    BigDecimal getLargerSumCoefficient() {
        return largerSumCoefficient;
    }

    BigDecimal getInsuranceBoundary() {
        return insuranceBoundary;
    }
}
