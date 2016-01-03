# AccountL
Java library for interest calculation of saving

# Usage
```
// Create FixedDeposit instance
FixedDeposit fd = new FixedDeposit();
fd.setStartDate(new DateTime());
fd.setMonths(12);
fd.setInterestRate(0.027);
fd.setTaxRate(0.154);
fd.setSeedMoney(10000000L);

// Get money and interest after given months
fd.moneyAtTheLastMonth(5);
fd.interestAtTheLastMonth(23);
```
