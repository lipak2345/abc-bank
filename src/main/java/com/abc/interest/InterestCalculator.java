package com.abc.interest;


import com.abc.Transaction;
import com.abc.account.AccountBase;

import java.util.SortedSet;

public interface InterestCalculator {
    default double calculateInterest(AccountBase a) {
        return calculateInterest(a.getTransactions());
    }

    double calculateInterest(SortedSet<Transaction> txns);


    static double sumTransactions(SortedSet<Transaction> txns) {
        double amount = 0.0;
        for(Transaction txn: txns) {
            amount += txn.getAmount();
        }

        return amount;
    }
}
