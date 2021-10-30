package com.pm.homework.beans.policy.object.subobject;

import com.pm.homework.beans.ObjectUtil;

import java.math.BigDecimal;
import java.util.Objects;

public class PolicySubObject {
    private final String name;
    private final RiskType riskType;
    private final BigDecimal sumInsured;

    public PolicySubObject(String name, RiskType riskType, BigDecimal sumInsured) {
        this.name = name;
        this.riskType = ObjectUtil.defaultOnNull(riskType, RiskType.FIRE);
        this.sumInsured = ObjectUtil.defaultOnNull(sumInsured, BigDecimal.ZERO);
    }

    public String getName() {
        return name;
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
        return Objects.equals(name, that.name) && riskType == that.riskType && Objects.equals(sumInsured, that.sumInsured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, riskType, sumInsured);
    }
}
