package com.pm.homework.policy;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;

import java.math.BigDecimal;

public class PolicyCalculator {
    private BigDecimal sumInsured;
    private BigDecimal coefficient;

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

    /*    PREMIUM_FIRE = SUM_INSURED_FIRE * COEFFICIENT_FIRE
        SUM_INSURED_FIRE - total sum insured of all policy's sub-objects with type "Fire"
        COEFFICIENT_FIRE - by default 0.014 but if SUM_INSURED_FIRE is over 100 then 0.024
    */
    public BigDecimal calculatePremium() {
        return sumInsured.multiply(coefficient);
    }

    BigDecimal getCoefficient() {
        return coefficient;
    }
}
