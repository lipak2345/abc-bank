package com.abc.account;

import com.abc.Transaction;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AbstractAccount {

    public static final Comparator<Transaction> TRANSACTION_COMPARATOR = new Comparator<Transaction>() {
        public int compare(Transaction o1, Transaction o2) {
            int ret = o1.getTransactionDate().compareTo(o2.getTransactionDate());
            return (ret == 0) ? o1.getTransactionId().compareTo(o2.getTransactionId()) : ret;
        }
    };

    public SortedSet<Transaction> getTransactions() {
        return Collections.unmodifiableSortedSet(transactions);
    }

    private SortedSet<Transaction> transactions;

    public enum AccountType {
        CHECKING("Checking Account"),
        SAVINGS("Savings Account"),
        MAXI_SAVINGS("Maxi Savings Account");

        public String getName() {
            return name;
        }

        private final String name;

        AccountType(String name) {
            this.name = name;
        }
    };

    public AbstractAccount() {
        this.transactions = new TreeSet<Transaction>(TRANSACTION_COMPARATOR);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public abstract double interestEarned();

    public double sumTransactions(){
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }

    public abstract AccountType getAccountType();
}
