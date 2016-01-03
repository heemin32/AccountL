package com.github.heemin32.model;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixedDepositTest {
    private FixedDeposit fd;

    @Before
    public void init() {
        fd = new FixedDeposit();
        fd.setStartDate(new DateTime().minusMonths(3).minusDays(3));
        fd.setMonths(12);
        fd.setInterestRate(0.027);
        fd.setTaxRate(0.154);
        fd.setSeedMoney(10000000L);
    }

    @Test
    public void simpleTypeTest() {
        fd.setInterestType(InterestType.Simple);
        assertEquals(10000000L, (long)fd.moneyAtThisMonth());
        assertEquals((long)(10000000 * 0.027 * 0.846), (long)fd.interestAtTheLastMonth());
    }

    @Test
    public void compoundingTypeTest() {
        fd.setInterestType(InterestType.Compounding);
        assertEquals(10000000L, (long)fd.moneyAtTheLastMonth());
        assertEquals(231268L, (long)fd.interestAtTheLastMonth());
    }

}
