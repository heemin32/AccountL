# AccountL
Java library for interest calculation of saving

# Usage
```java
// Create FixedDeposit instance
FixedDeposit fd = new FixedDeposit();
fd.setStartDate(new DateTime());
fd.setMonths(12);
fd.setInterestRate(0.027);
fd.setTaxRate(0.154);
fd.setSeedMoney(10000000L);

// Get money and interest after given months
fd.moneyAtTheLastMonth();
fd.interestAtTheLastMonth();
```

```java
// Create RecurringDeposit instance
RecurringDeposit rd = new RecurringDeposit();
rd.setStartDate(new DateTime());
rd.setMonths(12);
rd.setInterestRate(0.032);
rd.setTaxRate(0.154);
rd.setSeedMoney(100000L);

// Get money and interest after given months
fd.moneyAtTheLastMonth();
fd.interestAtTheLastMonth();
```
