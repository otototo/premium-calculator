package com.pm.homework;

import com.pm.homework.policy.Policy;
import com.pm.homework.policy.RiskType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PremiumCalculator {

    public static String calculate(Policy policy) {
        String result = "0.00 EUR";
        if (policy != null) {
            BigDecimal premiumFire = calculatePremium(policy, RiskType.FIRE);
            BigDecimal premiumTheft = calculatePremium(policy, RiskType.THEFT);
            BigDecimal premium = premiumFire.add(premiumTheft);
            result = premium.setScale(2, RoundingMode.HALF_UP) + " EUR";
        }
        return result;
    }

    private static BigDecimal calculatePremium(Policy policy, RiskType riskType) {
        var sumInsured = policy.getInsuredAmountByType(riskType);
        var coefficient = riskType.getCoefficient(sumInsured);
        return sumInsured.multiply(coefficient);
    }
}
