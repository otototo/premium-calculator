package com.pm.homework.beans.policy;

import com.pm.homework.beans.ObjectUtil;
import com.pm.homework.beans.policy.object.PolicyObject;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Policy {
    private final String policyNumber;
    private final PolicyStatus status;
    private final Set<PolicyObject> policyObjects;

    public Policy(String policyNumber, PolicyStatus status, Set<PolicyObject> policyObjects) {
        this.policyNumber = policyNumber;
        this.status = ObjectUtil.defaultOnNull(status, PolicyStatus.REGISTERED);
        this.policyObjects = ObjectUtil.defaultOnNull(policyObjects, Collections.emptySet());
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public Set<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(policyNumber, policy.policyNumber) && status == policy.status && Objects.equals(policyObjects, policy.policyObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber, status, policyObjects);
    }

}
