package com.pm.homework.policy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class PolicyObjectTest {
    private static final PolicySubObject POLICY_SUB_OBJECT_1 = new PolicySubObject("1322", RiskType.THEFT, BigDecimal.ONE);
    private static final PolicySubObject POLICY_SUB_OBJECT_2 = new PolicySubObject("1322", RiskType.THEFT, BigDecimal.ONE);
    private static final PolicySubObject POLICY_SUB_OBJECT_3 = new PolicySubObject("1322", RiskType.FIRE, BigDecimal.ONE);

    @Test
    void verifyBeanEquality() {
        PolicyObject po1 = new PolicyObject("132", Set.of(POLICY_SUB_OBJECT_1));
        PolicyObject po2 = new PolicyObject("132", Set.of(POLICY_SUB_OBJECT_2));
        PolicyObject po3 = new PolicyObject("132", Set.of(POLICY_SUB_OBJECT_3));

        Assertions.assertEquals(po1, po2);
        Assertions.assertEquals(po1.hashCode(), po2.hashCode());
        Assertions.assertNotEquals(po1, po3);
        Assertions.assertNotEquals(po1.hashCode(), po3.hashCode());
    }

    @Test
    void whenNoMatchingRiskTypeThenSumZero() {
        PolicyObject po1 = new PolicyObject("132", Set.of(POLICY_SUB_OBJECT_1));
        Assertions.assertEquals(BigDecimal.ZERO, po1.getInsuredAmountByType(RiskType.FIRE));

        PolicyObject po2 = new PolicyObject("132", Set.of(POLICY_SUB_OBJECT_3));
        Assertions.assertEquals(BigDecimal.ZERO, po2.getInsuredAmountByType(RiskType.THEFT));

        PolicyObject po3 = new PolicyObject("132", Collections.emptySet());
        Assertions.assertEquals(BigDecimal.ZERO, po3.getInsuredAmountByType(RiskType.THEFT));
    }
}
