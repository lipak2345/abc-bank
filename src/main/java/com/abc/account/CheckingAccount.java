package com.abc.account;


import com.abc.interest.CheckingInterest;

public class CheckingAccount extends AccountBase {

    public CheckingAccount() {
        super(new CheckingInterest(), AccountType.CHECKING);
    }

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
