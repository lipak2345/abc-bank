package com.abc;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Immutable class to represent transaction.
 */
public final class Transaction {

    public UUID getTransactionId() {
        return transactionId;
    }

    private final UUID transactionId = UUID.randomUUID();

    //TODO: Convert to a proper money/currency handling class or at least use BigDecimal for amount
    private final double amount;

    //Using Java 8 immutable LocalDateTime. for earlier versions of Java it is better to use something like Joda time
    private final LocalDateTime transactionDate;

    /**
     * Ctor using current date-time for transaction.
     *
     * @param amount the amount of the transaction
     */
    public Transaction(double amount) {
        this(amount, null);
    }

    /**
     * Ctor which accepts the date and time of the transaction.
     *
     * @param amount the amount of the transaction
     * @param date the date-time of the transaction. If null use the current date-time
     */
    public Transaction(double amount, LocalDateTime date) {
        this.amount = amount;
        this.transactionDate = (date != null)? date: LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
}
