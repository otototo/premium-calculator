package com.pm.homework.beans.policy.object;

import com.pm.homework.beans.ObjectUtil;
import com.pm.homework.beans.policy.object.subobject.PolicySubObject;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class PolicyObject {
    private final String name;
    private final Set<PolicySubObject> policySubObjects;

    public PolicyObject(String name, Set<PolicySubObject> policySubObjects) {
        this.name = name;
        this.policySubObjects = ObjectUtil.defaultOnNull(policySubObjects, Collections.emptySet());
    }

    public String getName() {
        return name;
    }

    public Set<PolicySubObject> getPolicySubObjects() {
        return policySubObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyObject that = (PolicyObject) o;
        return Objects.equals(name, that.name) && Objects.equals(policySubObjects, that.policySubObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, policySubObjects);
    }

}
