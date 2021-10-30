package com.pm.homework;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.PolicyStatus;
import com.pm.homework.beans.policy.object.PolicyObject;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;
import com.pm.homework.beans.policy.object.subobject.RiskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

public class PremiumCalculatorTest {

    private static final String POLICY_NUMBER = "LV20-02-100000-5";
    private static final PolicyStatus STATUS = PolicyStatus.APPROVED;

    static Stream<Arguments> fireAndTheftInsuranceSource() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(100.00), BigDecimal.valueOf(8.00), "2.28 EUR"),
                Arguments.of(BigDecimal.valueOf(500.00), BigDecimal.valueOf(102.51), "17.13 EUR")
        );
    }

    @ParameterizedTest
    @MethodSource("fireAndTheftInsuranceSource")
    public void givenFireAndTheftInsuranceReturnCorrectPremium(BigDecimal fireInsuranceAmount,
                                                               BigDecimal theftInsuranceAmount,
                                                               String expectedPremium) {
        //given
        PolicySubObject fireInsurance = new PolicySubObject("fireInsurance", RiskType.FIRE, fireInsuranceAmount);
        PolicySubObject theftInsurance = new PolicySubObject("theftInsurance", RiskType.THEFT, theftInsuranceAmount);
        PolicyObject insurancePackage = new PolicyObject("Insurance-Obj-1", Set.of(theftInsurance, fireInsurance));
        Set<PolicyObject> policyObjects = Set.of(insurancePackage);
        Policy policy = new Policy(POLICY_NUMBER, STATUS, policyObjects);

        //when
        String premium = PremiumCalculator.calculate(policy);

        //then
        Assertions.assertEquals(expectedPremium, premium);
    }

    @Test
    void givenNullValuesAsInputsCalculatorDoesNotBreak() {
        String expect = "0.00 EUR";
        Assertions.assertEquals(expect, PremiumCalculator.calculate(null));
        Assertions.assertEquals(expect, PremiumCalculator.calculate(
                new Policy(null, null, null)));
        Assertions.assertEquals(expect, PremiumCalculator.calculate(
                new Policy(null, null,
                        Set.of(new PolicyObject(null, null)))));
        Assertions.assertEquals(expect, PremiumCalculator.calculate(
                new Policy(null, null,
                        Set.of(new PolicyObject(null,
                                Set.of(new PolicySubObject(null, null, null)))))));
    }
}
