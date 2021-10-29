package com.pm.homework.beans.policy.object;

import com.pm.homework.beans.policy.object.subobject.PolicySubObject;
import com.pm.homework.beans.policy.object.subobject.RiskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

public class PolicyObjectTest {
    @Test
    void verifyBeanEquality() {
        PolicySubObject pso1 = new PolicySubObject("1322", RiskType.THEFT, BigDecimal.ONE);
        PolicySubObject pso2 = new PolicySubObject("1322", RiskType.THEFT, BigDecimal.ONE);
        PolicySubObject pso3 = new PolicySubObject("1322", RiskType.FIRE, BigDecimal.ONE);
        PolicyObject po1 = new PolicyObject("132", Set.of(pso1));
        PolicyObject po2 = new PolicyObject("132", Set.of(pso2));
        PolicyObject po3 = new PolicyObject("132", Set.of(pso3));

        Assertions.assertEquals(po1, po2);
        Assertions.assertEquals(po1.hashCode(), po2.hashCode());
        Assertions.assertEquals(po1.getName(), po3.getName());
        Assertions.assertNotEquals(po1, po3);
        Assertions.assertNotEquals(po1.hashCode(), po3.hashCode());
    }
}
