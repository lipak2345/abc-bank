package com.abc;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Cannot add null customer");
        }
        customers.add(customer);
    }

    public String customerSummary() {
        //TODO: Refactor to return a CustomerSummary object which will be easy to work/test.
        StringBuilder custBuilder = new StringBuilder("Customer Summary");

        for (Customer c : customers) {
            custBuilder.append("\n - ")
                    .append(c.getName())
                    .append(" (")
                    .append(format(c.getNumberOfAccounts(), "account"))
                    .append(')');
        }
        return custBuilder.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        if (word == null) {
            throw new IllegalArgumentException("Argument word cannot be null");
        }
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        Customer firstCustomer = (customers.size() > 0) ? customers.get(0) : null;
        return (firstCustomer != null)? firstCustomer.getName(): null;
    }
}
