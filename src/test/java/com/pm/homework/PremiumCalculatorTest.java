package com.pm.homework;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.beans.policy.PolicyStatus;
import com.pm.homework.beans.policy.object.PolicyObject;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;
import com.pm.homework.beans.policy.object.subobject.RiskType;
import org.junit.jupiter.api.Assertions;
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
                Arguments.of(BigDecimal.valueOf(100.00), BigDecimal.valueOf(8.00), BigDecimal.valueOf(2.28)),
                Arguments.of(BigDecimal.valueOf(500.00), BigDecimal.valueOf(102.51), BigDecimal.valueOf(17.13))
        );
    }

    @ParameterizedTest
    @MethodSource("fireAndTheftInsuranceSource")
    public void givenFireAndTheftInsuranceReturnCorrectPremium(BigDecimal fireInsuranceAmount,
                                                               BigDecimal theftInsuranceAmount,
                                                               BigDecimal expectedPremium) {
        //given
        PolicySubObject fireInsurance = new PolicySubObject("fireInsurance", RiskType.FIRE, fireInsuranceAmount);
        PolicySubObject theftInsurance = new PolicySubObject("theftInsurance", RiskType.THEFT, theftInsuranceAmount);
        PolicyObject insurancePackage = new PolicyObject("Insurance-Obj-1", Set.of(theftInsurance, fireInsurance));
        Set<PolicyObject> policyObjects = Set.of(insurancePackage);
        Policy policy = new Policy(POLICY_NUMBER, STATUS, policyObjects);

        //when
        BigDecimal premium = PremiumCalculator.calculate(policy);

        //then
        Assertions.assertEquals(expectedPremium, premium);
    }

}
