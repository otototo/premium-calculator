package com.pm.homework;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;
import com.pm.homework.beans.policy.object.subobject.RiskType;

import java.math.BigDecimal;

public class TheftPolicyCalculator {
    static final BigDecimal DEFAULT_COEFFICIENT = BigDecimal.valueOf(0.11);
    static final BigDecimal LARGER_SUM_COEFFICIENT = BigDecimal.valueOf(0.05);
    private static final BigDecimal INSURANCE_BOUNDARY = BigDecimal.valueOf(15);


    private final BigDecimal sumInsured;
    private final BigDecimal coefficient;

    public TheftPolicyCalculator(Policy policy) {
        this.sumInsured = policy.getPolicyObjects().stream().flatMap(p -> p.getPolicySubObjects().stream())
                .filter(pso -> RiskType.THEFT == pso.getRiskType())
                .map(PolicySubObject::getSumInsured)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        this.coefficient = sumInsured.compareTo(INSURANCE_BOUNDARY) > 0 ? LARGER_SUM_COEFFICIENT : DEFAULT_COEFFICIENT;
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
