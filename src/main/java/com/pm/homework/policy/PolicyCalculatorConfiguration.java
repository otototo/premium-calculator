package com.pm.homework.policy;

import com.pm.homework.beans.policy.object.subobject.PolicySubObject;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class PolicyCalculatorConfiguration {
    private final Predicate<PolicySubObject> policyTypePredicate;
    private final BigDecimal insuranceBoundary;
    private final BigDecimal defaultCoefficient;
    private final BigDecimal largerSumCoefficient;

    public PolicyCalculatorConfiguration(Predicate<PolicySubObject> riskTypePredicate,
                                         BigDecimal insuranceBoundary,
                                         BigDecimal defaultCoefficient,
                                         BigDecimal largerSumCoefficient) {
        this.policyTypePredicate = riskTypePredicate;
        this.insuranceBoundary = insuranceBoundary;
        this.defaultCoefficient = defaultCoefficient;
        this.largerSumCoefficient = largerSumCoefficient;
    }

    public Predicate<PolicySubObject> getPolicyTypePredicate() {
        return policyTypePredicate;
    }

    public BigDecimal getInsuranceBoundary() {
        return insuranceBoundary;
    }

    public BigDecimal getDefaultCoefficient() {
        return defaultCoefficient;
    }

    public BigDecimal getLargerSumCoefficient() {
        return largerSumCoefficient;
    }
}
