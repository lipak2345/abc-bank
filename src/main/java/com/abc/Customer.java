package com.abc;

import com.abc.account.AbstractAccount;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<AbstractAccount> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<AbstractAccount>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(AbstractAccount account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (AbstractAccount a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder stmtBuilder = new StringBuilder("Statement for ");
        stmtBuilder.append(name)
                .append('\n');

        double total = 0.0;
        for (AbstractAccount a : accounts) {
            stmtBuilder.append('\n');
            statementForAccount(a, stmtBuilder);
            stmtBuilder.append('\n');

            total += a.sumTransactions();
        }
        stmtBuilder.append("\nTotal In All Accounts ")
                .append(toDollars(total));

        return stmtBuilder.toString();
    }

    private void statementForAccount(AbstractAccount a, StringBuilder stmtBuilder) {
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
