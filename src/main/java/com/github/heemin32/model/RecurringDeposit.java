package com.github.heemin32.model;

import java.math.BigDecimal;

public class RecurringDeposit extends Deposit{
    @Override
    public Long moneyAfterMonths(int months) {
        return getSeedMoney() * months;
    }

    @Override
    public Long interestAfterMonths(int month) {
        Long interest;
        switch (getInterestType()) {
            case Simple:
                interest =  simpleInterestAt(month);
                break;
            case Compounding:
                interest = compoundingInterestAt(month);
                break;
            default:
                throw new RuntimeException("");
        }
        return Math.round(interest - (interest * getTaxRate()));
    }

    private Long simpleInterestAt(int month) {
        // interest = p * r/12 * n(n + 1)/2
        BigDecimal p = new BigDecimal(getSeedMoney());
        BigDecimal r = new BigDecimal(getInterestRate());
        BigDecimal n = new BigDecimal(month);
        BigDecimal rOver12 = r.divide(TWELVE, PRECISION, BigDecimal.ROUND_HALF_UP);
        return p.multiply(rOver12).multiply(n).multiply(n.add(ONE)).divide(TWO).add(POINT_FIVE).longValue();
    }

    private Long compoundingInterestAt(int month) {
        // rOver12 = r / 12
        // interest = p * {(1+r12)^(n+1) - (1 + r12)} / r12 - np
        BigDecimal p = new BigDecimal(getSeedMoney());
        BigDecimal r = new BigDecimal(getInterestRate());
        BigDecimal n = new BigDecimal(month);
        BigDecimal rOver12 = r.divide(TWELVE, PRECISION, BigDecimal.ROUND_HALF_UP);
        BigDecimal rOver12PlusOne = rOver12.add(ONE);
        return p.multiply(rOver12PlusOne.pow(n.intValue() + 1).subtract(rOver12PlusOne)).divide(rOver12, PRECISION, BigDecimal.ROUND_HALF_UP).subtract(n.multiply(p)).add(POINT_FIVE).longValue();
    }
}
