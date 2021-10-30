package com.pm.homework;

import com.pm.homework.beans.policy.Policy;
import com.pm.homework.policy.FirePolicyCalculator;
import com.pm.homework.policy.TheftPolicyCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PremiumCalculator {

    public static String calculate(Policy policy) {
        String result = "0.00 EUR";
        if (policy != null) {
            BigDecimal premiumFire = new FirePolicyCalculator(policy).calculatePremium();
            BigDecimal premiumTheft = new TheftPolicyCalculator(policy).calculatePremium();
            BigDecimal premium = premiumFire.add(premiumTheft);
            result = premium.setScale(2, RoundingMode.UP) + " EUR";
        }
        return result;
    }
}
