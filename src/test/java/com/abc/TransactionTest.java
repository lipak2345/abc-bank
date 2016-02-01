package com.abc;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {
    private static final double EPSILON = 1e-15;
    private final Transaction t = new Transaction(5);


    @Test
    public void testGetTransactionId() throws Exception {
        Transaction t1 = new Transaction(2);
        Transaction t2 = new Transaction(2);
        assertTrue(t2.getTransactionId() > t1.getTransactionId());
    }

    @Test
    public void testGetAmount() throws Exception {
        assertEquals(5, t.getAmount(), EPSILON);
    }

    @Test
    public void testGetTransactionDate() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        Transaction t1 = new Transaction(7.0, date);

        assertEquals(date, t1.getTransactionDate());
    }
}
