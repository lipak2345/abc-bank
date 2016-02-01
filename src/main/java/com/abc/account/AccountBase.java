package com.abc.account;

import com.abc.Transaction;
import com.abc.interest.InterestCalculator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccountBase {

    public static final Comparator<Transaction> TRANSACTION_COMPARATOR = (Transaction o1, Transaction o2) -> {
        int ret = o1.getTransactionDate().compareTo(o2.getTransactionDate());
        return (ret == 0) ? o1.getTransactionId()-o2.getTransactionId() : ret;
    };
    public static final double DAYS_PER_YEAR = 365.0;

    public SortedSet<Transaction> getTransactions() {
        return Collections.unmodifiableSortedSet(transactions);
    }

    private SortedSet<Transaction> transactions;

    private InterestCalculator defaultInterestCalculator;
    private AccountType accountType;

    protected AccountBase(InterestCalculator defaultInterestCalculator, AccountType accountType) {
        this.defaultInterestCalculator = defaultInterestCalculator;
        this.accountType = accountType;
        this.transactions = new TreeSet<>(TRANSACTION_COMPARATOR);
    }

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
    }

    public void deposit(double amount) {
        deposit(amount, null);
    }

    public void deposit(double amount, LocalDateTime dateTime) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, dateTime));
        }
    }

    public void withdraw(double amount) {
        withdraw(amount, null);
    }

    public void withdraw(double amount, LocalDateTime dateTime) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            //TODO: Need to add some logic for overdraft based on requirements
            transactions.add(new Transaction(-amount, dateTime));
        }
    }

    public void transferTo(AccountBase toAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            withdraw(amount);
            toAccount.deposit(amount);
        }
    }

    public void transferFrom(AccountBase fromAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            fromAccount.withdraw(amount);
            deposit(amount);
        }
    }

    public double interestEarned() {
        return defaultInterestCalculator.calculateInterest(this);
    }

    public double interestEarned(InterestCalculator calc) {
        return calc.calculateInterest(this);
    }

    public double sumTransactions(){
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }

    public double dailyAccrualInterest(InterestCalculator interestCalculator) {
        final SortedSet<Transaction> txns = this.getTransactions();
        if (txns.size() == 0) {
            return 0.0;
        }

        LocalDate current = txns.first().getTransactionDate().toLocalDate();
        double interestAccrual = 0.0;
        TreeSet<Transaction> transactionsSoFar = new TreeSet<>(TRANSACTION_COMPARATOR);
        for(Transaction txn: txns) {
            LocalDate txnDate = txn.getTransactionDate().toLocalDate();
            if (current.isEqual(txnDate)) {
                transactionsSoFar.add(txn);
            } else {
                long days = Duration.between(current, txnDate).toDays();
                double interestForOneDay = interestCalculator.calculateInterest(transactionsSoFar) / DAYS_PER_YEAR;
                interestAccrual += days * interestForOneDay;
                current = txnDate;
            }
        }
        return interestAccrual;
    }

    public double dailyAccrualInterest() {
        return this.dailyAccrualInterest(defaultInterestCalculator);
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
