package com.abc.interest;

import com.abc.Transaction;
import com.abc.account.AccountBase;

import java.util.SortedSet;

public class SavingsInterest implements InterestCalculator {

    @Override
    public double calculateInterest(SortedSet<Transaction> txns) {
        double amount = InterestCalculator.sumTransactions(txns);
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;
    }
}
