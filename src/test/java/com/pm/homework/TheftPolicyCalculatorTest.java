package com.pm.homework;

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

class TheftPolicyCalculatorTest {

    private static final String POLICY_NUMBER = "123";

    @Test
    void givenThereIsNoInsuranceThenUseDefaultCoefficient() {
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, Collections.emptySet());

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        Assertions.assertEquals(TheftPolicyCalculator.DEFAULT_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenThereIsNoTheftInsuranceThenUseDefaultCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.FIRE, BigDecimal.valueOf(2.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.FIRE, BigDecimal.valueOf(3.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        Assertions.assertEquals(TheftPolicyCalculator.DEFAULT_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenTheftInsuranceIsSmallThenUseDefaultCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.THEFT, BigDecimal.valueOf(2.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.THEFT, BigDecimal.valueOf(3.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        Assertions.assertEquals(TheftPolicyCalculator.DEFAULT_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenTheftInsuranceIsLargeSingleThenUseDefaultCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.THEFT, BigDecimal.valueOf(300.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        Assertions.assertEquals(TheftPolicyCalculator.LARGER_SUM_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenTheftInsuranceIsLargeMultiThenUseDefaultCoefficient() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.THEFT, BigDecimal.valueOf(8.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.THEFT, BigDecimal.valueOf(8.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        Assertions.assertEquals(TheftPolicyCalculator.LARGER_SUM_COEFFICIENT, calculator.getCoefficient());
    }

    @Test
    void givenNoTheftInsuranceThenCalculatedPremiumZero() {
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, Collections.emptySet());

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        Assertions.assertEquals(BigDecimal.ZERO, calculator.calculatePremium().setScale(0, RoundingMode.DOWN));
    }

    @Test
    void givenTheftInsuranceWithDefaultCoefficientThenCalculatePremium() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.THEFT, BigDecimal.valueOf(10.00));
        PolicySubObject nso2 = new PolicySubObject("nso2", RiskType.THEFT, BigDecimal.valueOf(5.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1, nso2));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        BigDecimal actual = calculator.calculatePremium().setScale(2, RoundingMode.DOWN);
        BigDecimal expectedPremium = BigDecimal.valueOf(1.65);
        Assertions.assertEquals(expectedPremium, actual);
    }

    @Test
    void givenTheftInsuranceWithLargerCoefficientThenCalculatePremium() {
        PolicySubObject nso1 = new PolicySubObject("nso1", RiskType.THEFT, BigDecimal.valueOf(20.00));
        PolicyObject p1 = new PolicyObject("n1", Set.of(nso1));
        Set<PolicyObject> objects = Set.of(p1);
        Policy policy = new Policy(POLICY_NUMBER, PolicyStatus.APPROVED, objects);

        TheftPolicyCalculator calculator = new TheftPolicyCalculator(policy);
        BigDecimal expectedPremium = BigDecimal.ONE;
        Assertions.assertEquals(expectedPremium, calculator.calculatePremium().setScale(0, RoundingMode.DOWN));
    }
}