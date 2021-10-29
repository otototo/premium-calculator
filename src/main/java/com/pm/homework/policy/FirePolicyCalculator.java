package com.pm.homework.policy;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.object.subobject.RiskType;
import com.pm.homework.policy.PolicyCalculator;
import com.pm.homework.policy.PolicyCalculatorConfiguration;

import java.math.BigDecimal;

public class FirePolicyCalculator extends PolicyCalculator {
    static final BigDecimal DEFAULT_COEFFICIENT = BigDecimal.valueOf(0.014);
    static final BigDecimal LARGER_SUM_COEFFICIENT = BigDecimal.valueOf(0.024);
    private static final BigDecimal INSURANCE_BOUNDARY = BigDecimal.valueOf(100);
    private static final PolicyCalculatorConfiguration CONFIGURATION = new PolicyCalculatorConfiguration(
            policySubObject -> RiskType.FIRE == policySubObject.getRiskType(),
            INSURANCE_BOUNDARY, DEFAULT_COEFFICIENT, LARGER_SUM_COEFFICIENT
    );
    public FirePolicyCalculator(Policy policy) {
        super(CONFIGURATION, policy);
    }
}
