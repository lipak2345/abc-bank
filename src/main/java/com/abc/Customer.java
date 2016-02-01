package com.abc;

import com.abc.account.AccountBase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<AccountBase> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(AccountBase account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (AccountBase a : accounts)
            total += a.interestEarned();
        return total;
    }

    public void transfer(AccountBase from, AccountBase to, double amount) {
        //NOTE: Not thread safe and does not use transactions.
        //TODO: Add check to see if both accounts are from the same customer.

        from.withdraw(amount);
        to.deposit(amount);
    }

    public String getStatement() {
        //TODO: Refactor to return a CustomerStatement object which will be easy to work/test.

        StringBuilder stmtBuilder = new StringBuilder("Statement for ");
        stmtBuilder.append(name)
                .append('\n');

        double total = 0.0;
        for (AccountBase a : accounts) {
            stmtBuilder.append('\n');
            statementForAccount(a, stmtBuilder);
            stmtBuilder.append('\n');

            total += a.sumTransactions();
        }
        stmtBuilder.append("\nTotal In All Accounts ")
                .append(toDollars(total));

        return stmtBuilder.toString();
    }

    private void statementForAccount(AccountBase a, StringBuilder stmtBuilder) {
        stmtBuilder
                .append(a.getAccountType().getName())
                .append('\n');

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            stmtBuilder.append("  ")
                    .append((t.getAmount() < 0 ? "withdrawal" : "deposit"))
                    .append(' ')
                    .append(toDollars(t.getAmount()))
                    .append('\n');
            total += t.getAmount();
        }

        stmtBuilder.append("Total ")
                .append(toDollars(total));
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
