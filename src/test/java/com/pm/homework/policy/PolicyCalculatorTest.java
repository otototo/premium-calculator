package com.pm.homework.policy;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.PolicyStatus;
import com.pm.homework.beans.policy.object.PolicyObject;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;
import com.pm.homework.beans.policy.object.subobject.RiskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

class PolicyCalculatorTest {

    private static final String POLICY_NUMBER = "in-987";
    private static final BigDecimal INSURANCE_BOUNDARY = BigDecimal.TEN;
    private static final BigDecimal DEFAULT_COEFFICIENT = BigDecimal.ONE;
    private static final BigDecimal LARGER_SUM_COEFFICIENT = BigDecimal.TEN;

    private static final PolicyCalculatorConfiguration CONFIGURATION = new PolicyCalculatorConfiguration(
            pso -> true, INSURANCE_BOUNDARY, DEFAULT_COEFFICIENT, LARGER_SUM_COEFFICIENT
    );

    @Test
    void givenThereIsNoInsuranceThenUseDefaultCoefficient() {
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, Collections.emptySet());

        PolicyCalculator calculator = new PolicyCalculator(CONFIGURATION, policy);
        Assertions.assertEquals(DEFAULT_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenInsuranceIsSmallThenUseDefaultCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.FIRE, BigDecimal.valueOf(2.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.THEFT, BigDecimal.valueOf(3.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        PolicyCalculator calculator = new PolicyCalculator(CONFIGURATION, policy);
        Assertions.assertEquals(DEFAULT_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenInsuranceIsLargeSingleThenUseLargeSumCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.THEFT, BigDecimal.valueOf(300.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        PolicyCalculator calculator = new PolicyCalculator(CONFIGURATION, policy);
        Assertions.assertEquals(LARGER_SUM_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenInsuranceIsLargeMultiThenUseLargeSumCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.FIRE, BigDecimal.valueOf(8.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.THEFT, BigDecimal.valueOf(8.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        PolicyCalculator calculator = new PolicyCalculator(CONFIGURATION, policy);
        Assertions.assertEquals(LARGER_SUM_COEFFICIENT, calculator.getCoefficient());
    }
}