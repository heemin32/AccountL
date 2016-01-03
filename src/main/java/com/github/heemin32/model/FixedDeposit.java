package com.github.heemin32.model;

import java.math.BigDecimal;

public class FixedDeposit extends Deposit {

    @Override
    public Long moneyAfterMonths(int months) {
        return getSeedMoney();
    }

    @Override
    public Long interestAfterMonths(int month) {
        double interest;
        switch (getInterestType()) {
            case Simple:
                interest =  simpleInterestAt(month);
                break;
            case Compounding:
                interest = compoundingInterestAt(month);
                break;
            default:
                throw new RuntimeException("Interest type is not valid: " + getInterestType());
        }
        return Math.round(interest - (interest * getTaxRate()));
    }

    private Long simpleInterestAt(int month) {
        // p * (r / 12) * n
        BigDecimal p = new BigDecimal(getSeedMoney());
        BigDecimal r = new BigDecimal(getInterestRate());
        BigDecimal n = new BigDecimal(month);
        BigDecimal rOver12 = r.divide(TWELVE, PRECISION, BigDecimal.ROUND_HALF_UP);
        return p.multiply(rOver12).multiply(n).add(POINT_FIVE).longValue();
    }

    private Long compoundingInterestAt(int month) {
        // p * (1 + r / 12)^n - p
        BigDecimal p = new BigDecimal(getSeedMoney());
        BigDecimal r = new BigDecimal(getInterestRate());
        BigDecimal n = new BigDecimal(month);
        BigDecimal rOver12 = r.divide(TWELVE, PRECISION, BigDecimal.ROUND_HALF_UP);
        return p.multiply(rOver12.add(ONE).pow(n.intValue())).subtract(p).add(POINT_FIVE).longValue();
    }
}
