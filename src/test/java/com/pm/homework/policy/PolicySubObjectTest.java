package com.pm.homework.policy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PolicySubObjectTest {

    @Test
    void verifyBeanEquality() {
        PolicySubObject pso1 = new PolicySubObject("1322", RiskType.FIRE, BigDecimal.valueOf(999.9999));
        PolicySubObject pso2 = new PolicySubObject("1322", RiskType.FIRE, BigDecimal.valueOf(999.9999));
        PolicySubObject pso3 = new PolicySubObject("1322", RiskType.THEFT, BigDecimal.valueOf(999.00));
        PolicySubObject pso4 = new PolicySubObject("1322", RiskType.THEFT, BigDecimal.valueOf(999.0));

        Assertions.assertEquals(pso1, pso2);
        Assertions.assertEquals(pso1.hashCode(), pso2.hashCode());
        Assertions.assertNotEquals(pso1, pso3);
        Assertions.assertNotEquals(pso1.hashCode(), pso3.hashCode());
        Assertions.assertEquals(pso3, pso4);
        Assertions.assertEquals(pso3.hashCode(), pso4.hashCode());
    }
}