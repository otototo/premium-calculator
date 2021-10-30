package com.pm.homework.policy;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;

import java.math.BigDecimal;

public class PolicyCalculator {
    private final BigDecimal sumInsured;
    private final BigDecimal coefficient;

    public PolicyCalculator(PolicyCalculatorConfiguration configuration,
                            Policy policy) {
        this.sumInsured = policy.getPolicyObjects().stream().flatMap(p -> p.getPolicySubObjects().stream())
                .filter(configuration.getPolicyTypePredicate())
                .map(PolicySubObject::getSumInsured)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        this.coefficient = sumInsured.compareTo(configuration.getInsuranceBoundary()) > 0 ?
                configuration.getLargerSumCoefficient() : configuration.getDefaultCoefficient();
    }

    public BigDecimal calculatePremium() {
        return sumInsured.multiply(coefficient);
    }

    BigDecimal getCoefficient() {
        return coefficient;
    }
}
