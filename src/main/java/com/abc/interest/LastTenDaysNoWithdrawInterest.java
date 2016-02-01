package com.abc.interest;

import com.abc.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.SortedSet;

public class LastTenDaysNoWithdrawInterest implements InterestCalculator {

    public static final LocalDateTime TEN_DAYS_AGO = LocalDate.now().atStartOfDay().minusDays(10);

//    public double calculateInterest(AccountBase a) {
//        double amount = a.sumTransactions();
//        double rate = getRate(a.getTransactions());
//
//        return amount * rate;
//    }

    private double getRate(SortedSet<Transaction> txns) {
        double rate = 0.05;
        for(Transaction txn: txns) {
            if (txn.getTransactionDate().isAfter(TEN_DAYS_AGO)) {
                if (txn.getAmount() < 0) {
                    rate = 0.001;
                    break;
                }
            }
        }
        return rate;
    }

    public double calculateInterest(SortedSet<Transaction> txns) {
        double rate = getRate(txns);
        double amountSoFar = InterestCalculator.sumTransactions(txns);
        return amountSoFar * rate;
    }
}
