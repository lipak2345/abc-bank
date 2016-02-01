package com.abc.interest;

import com.abc.Transaction;

import java.util.SortedSet;

public class MaxiSavingsInterest implements InterestCalculator {

    private double getInterestForAmount(double amount) {
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;
    }

    public double calculateInterest(SortedSet<Transaction> txns) {

        double amountSoFar = InterestCalculator.sumTransactions(txns);
        return getInterestForAmount(amountSoFar);
    }
}
