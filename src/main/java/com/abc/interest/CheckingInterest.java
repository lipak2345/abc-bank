package com.abc.interest;

import com.abc.Transaction;

import java.util.SortedSet;


public class CheckingInterest implements InterestCalculator {

    @Override
    public double calculateInterest(SortedSet<Transaction> txns) {
        double amount = InterestCalculator.sumTransactions(txns);
        return amount * 0.001;
    }
}
