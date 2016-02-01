package com.abc.account;

import com.abc.interest.MaxiSavingsInterest;

public class MaxiSavingsAccount extends AccountBase {
    public MaxiSavingsAccount() {
        super(new MaxiSavingsInterest(), AccountType.MAXI_SAVINGS);
    }

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;
    }

    @Override
    public AccountBase.AccountType getAccountType() {
        return AccountType.MAXI_SAVINGS;
    }
}
