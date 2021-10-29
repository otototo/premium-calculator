package com.pm.homework;

import com.pm.homework.beans.policy.Policy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class PremiumCalculator {

    /*
    PREMIUM = PREMIUM_FIRE + PREMIUM_THEFT */
    public static BigDecimal calculate(Policy policy) {
        BigDecimal premiumFire = new FirePolicyCalculator(policy).calculatePremium();
        BigDecimal premiumTheft = new TheftPolicyCalculator(policy).calculatePremium();
        BigDecimal premium = premiumFire.add(premiumTheft);
        return premium.setScale(2, RoundingMode.UP);
    }

}
