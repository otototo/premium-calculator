package com.pm.homework.policy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class PolicyObject {
    private final String name;
    private final Set<PolicySubObject> policySubObjects;

    public PolicyObject(String name, Set<PolicySubObject> policySubObjects) {
        this.name = name;
        this.policySubObjects = policySubObjects;
    }

    public BigDecimal getInsuredAmountByType(RiskType type) {
        return Optional.ofNullable(policySubObjects)
                .orElse(Collections.emptySet())
                .stream()
                .filter(policySubObject -> policySubObject.getRiskType() == type)
                .map(PolicySubObject::getSumInsured)
                .filter(Objects::nonNull)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
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
