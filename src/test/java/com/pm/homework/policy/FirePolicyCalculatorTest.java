package com.pm.homework.policy;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.PolicyStatus;
import com.pm.homework.beans.policy.object.PolicyObject;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;
import com.pm.homework.beans.policy.object.subobject.RiskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Set;

class FirePolicyCalculatorTest {

    public static final String POLICY_NUMBER = "123";

    @Test
    void givenThereIsNoFireInsuranceThenUseDefaultCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.THEFT, BigDecimal.valueOf(2.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.THEFT, BigDecimal.valueOf(3.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        FirePolicyCalculator calculator = new FirePolicyCalculator(policy);
        Assertions.assertEquals(FirePolicyCalculator.DEFAULT_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenNoFireInsuranceThenCalculatedPremiumZero() {
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, Collections.emptySet());

        FirePolicyCalculator calculator = new FirePolicyCalculator(policy);
        Assertions.assertEquals(BigDecimal.ZERO, calculator.calculatePremium().setScale(0, RoundingMode.DOWN));
    }

    @Test
    void givenFireInsuranceWithDefaultCoefficientThenCalculatePremium() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.FIRE, BigDecimal.valueOf(50.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.FIRE, BigDecimal.valueOf(50.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        FirePolicyCalculator calculator = new FirePolicyCalculator(policy);
        BigDecimal expectedPremium = BigDecimal.valueOf(1.4);
        Assertions.assertEquals(expectedPremium, calculator.calculatePremium().setScale(1, RoundingMode.DOWN));
    }

    @Test
    void givenFireInsuranceWithLargerCoefficientThenCalculatePremium() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.FIRE, BigDecimal.valueOf(101.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        FirePolicyCalculator calculator = new FirePolicyCalculator(policy);
        BigDecimal expectedPremium = BigDecimal.valueOf(2.424);
        Assertions.assertEquals(expectedPremium, calculator.calculatePremium().setScale(3, RoundingMode.DOWN));
    }

}