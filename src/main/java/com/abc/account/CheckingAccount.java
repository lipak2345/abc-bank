package com.abc.account;


public class CheckingAccount extends AbstractAccount {
    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        return amount * 0.001;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
