package com.pm.homework.beans.policy;

import com.pm.homework.beans.policy.object.PolicyObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

class PolicyTest {

    @Test
    void verifyBeanEquality() {
        PolicyObject po1 = new PolicyObject("456", Collections.emptySet());
        Policy p1 = new Policy("123", PolicyStatus.REGISTERED, Set.of(po1));
        Policy p2 = new Policy("123", PolicyStatus.REGISTERED, Set.of(po1));
        Policy p3 = new Policy("123", PolicyStatus.REGISTERED, Collections.emptySet());

        Assertions.assertEquals(p1, p2);
        Assertions.assertEquals(p1.hashCode(), p2.hashCode());
        Assertions.assertEquals(p1.getPolicyNumber(), p2.getPolicyNumber());
        Assertions.assertEquals(p1.getStatus(), p2.getStatus());
        Assertions.assertNotEquals(p1, p3);
        Assertions.assertNotEquals(p1.hashCode(), p3.hashCode());
    }

    @Test
    void defaultsNullsForStatusAndObjects() {
        Policy p = new Policy(null, null, null);
        Assertions.assertEquals(p.getStatus(), PolicyStatus.REGISTERED);
        Assertions.assertEquals(p.getPolicyObjects(), Collections.emptySet());
    }

}