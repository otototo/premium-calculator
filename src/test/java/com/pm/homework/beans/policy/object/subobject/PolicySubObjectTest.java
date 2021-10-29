package com.pm.homework.beans.policy.object.subobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PolicySubObjectTest {
    @Test
    void verifyBeanEquality() {
        PolicySubObject pso1 = new PolicySubObject("1322", RiskType.FIRE, BigDecimal.ONE);
        PolicySubObject pso2 = new PolicySubObject("1322", RiskType.FIRE, BigDecimal.ONE);
        PolicySubObject pso3 = new PolicySubObject("1322", RiskType.THEFT, BigDecimal.ONE);

        Assertions.assertEquals(pso1, pso2);
        Assertions.assertEquals(pso1.hashCode(), pso2.hashCode());
        Assertions.assertEquals(pso1.getName(), pso3.getName());
        Assertions.assertNotEquals(pso1, pso3);
        Assertions.assertNotEquals(pso1.hashCode(), pso3.hashCode());
    }
}