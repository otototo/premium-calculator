package com.pm.homework.policy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Policy {
    private final String policyNumber;
    private final PolicyStatus status;
    private final Set<PolicyObject> policyObjects;

    public Policy(String policyNumber, PolicyStatus status, Set<PolicyObject> policyObjects) {
        this.policyNumber = policyNumber;
        this.status = status;
        this.policyObjects = policyObjects;
    }

    public BigDecimal getInsuredAmountByType(RiskType type) {
        return Optional.ofNullable(policyObjects)
                .orElse(Collections.emptySet())
                .stream()
                .map(p -> p.getInsuredAmountByType(type))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
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
