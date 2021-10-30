package com.pm.homework.policy;

import java.math.BigDecimal;
import java.util.Objects;

public class PolicySubObject {
    private final String name;
    private final RiskType riskType;
    private final BigDecimal sumInsured;

    public PolicySubObject(String name, RiskType riskType, BigDecimal sumInsured) {
        this.name = name;
        this.riskType = riskType;
        this.sumInsured = sumInsured;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicySubObject that = (PolicySubObject) o;
        return Objects.equals(name, that.name) && riskType == that.riskType &&
                sumInsured.compareTo(that.sumInsured) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, riskType, sumInsured);
    }
}
