package com.abc.interest;

import com.abc.account.AccountBase;
import com.abc.account.MaxiSavingsAccount;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class MaxiSavingsInterestTest  {
    private static final double EPSILON = 1e-15;
    private static MaxiSavingsInterest msi = new MaxiSavingsInterest();

    @Test
    public void testCalculateInterest() throws Exception {
        AccountBase a = new MaxiSavingsAccount();
        a.deposit(3000, LocalDateTime.of(2016, 1, 12, 9, 20, 6));
        a.deposit(250, LocalDateTime.of(2016, 1, 13, 9, 30, 6));

        assertEquals(195.0, msi.calculateInterest(a), EPSILON);

    }

    @Test
    public void testDailyInterestAccrual() throws Exception {
        AccountBase a = new MaxiSavingsAccount();
        a.deposit(3000, LocalDateTime.of(2016, 1, 13, 9, 20, 6));
        a.deposit(250, LocalDateTime.of(2016, 1, 13, 9, 30, 6));

        assertEquals(195.0, msi.calculateInterest(a.getTransactions()), EPSILON);
    }
}