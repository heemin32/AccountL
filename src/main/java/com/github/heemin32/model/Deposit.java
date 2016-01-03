package com.github.heemin32.model;

import org.joda.time.DateTime;
import org.joda.time.Months;

import java.math.BigDecimal;

public abstract class Deposit {
    static protected final BigDecimal ONE = new BigDecimal(1);
    static protected final BigDecimal TWO = new BigDecimal(2);
    static protected final BigDecimal TWELVE = new BigDecimal(12);
    static protected final BigDecimal POINT_FIVE = new BigDecimal(0.5);
    static protected final int PRECISION = 100;

    private Double interestRate;
    private Double taxRate;
    private Long seedMoney;
    private DateTime startDate;
    private Integer months;
    private InterestType interestType;

    public DateTime getLastDate() {
        return startDate.plusMonths(months);
    }

    public Long moneyAtThisMonth() {
        return moneyAfterMonths(Months.monthsBetween(getStartDate(), DateTime.now()).getMonths());
    }
    public Long moneyAtTheLastMonth() {
        return moneyAfterMonths(months);
    }

    public Long interestAtThisMonth() {
        return interestAfterMonths(Months.monthsBetween(getStartDate(), DateTime.now()).getMonths());
    }

    public Long interestAtTheLastMonth() {
        return interestAfterMonths(months);
    }

    abstract public Long moneyAfterMonths(int months);
    abstract public Long interestAfterMonths(int months);


    // Getters, Setters
    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Long getSeedMoney() {
        return seedMoney;
    }

    public void setSeedMoney(Long seedMoney) {
        this.seedMoney = seedMoney;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public InterestType getInterestType() {
        return interestType;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }
}
