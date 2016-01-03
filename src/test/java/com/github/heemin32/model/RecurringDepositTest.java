package com.github.heemin32.model;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecurringDepositTest {
    private RecurringDeposit rd;

    @Before
    public void init() {
        rd = new RecurringDeposit();
        rd.setStartDate(new DateTime().minusMonths(3).minusDays(3));
        rd.setMonths(12);
        rd.setInterestRate(0.032);
        rd.setTaxRate(0.154);
        rd.setSeedMoney(100000L);
    }

    @Test
    public void simpleTypeTest() {
        rd.setInterestType(InterestType.Simple);
        assertEquals(300000L, (long)rd.moneyAtThisMonth());
        assertEquals(17597L, (long) rd.interestAtTheLastMonth());
    }

    @Test
    public void compoundingTypeTest() {
        rd.setInterestType(InterestType.Compounding);
        assertEquals(1200000L, (long)rd.moneyAtTheLastMonth());
        assertEquals(17770L, (long) rd.interestAtTheLastMonth());
    }
}
