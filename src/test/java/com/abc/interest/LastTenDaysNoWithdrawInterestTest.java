package com.abc.interest;

import com.abc.account.AccountBase;
import com.abc.account.MaxiSavingsAccount;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class LastTenDaysNoWithdrawInterestTest {
    private static final double EPSILON = 1e-15;


    @Test
    public void testCalculateInterest() throws Exception {
        LastTenDaysNoWithdrawInterest interestCalculator = new LastTenDaysNoWithdrawInterest();

        AccountBase a = new MaxiSavingsAccount();
        LocalDateTime today = LocalDateTime.now();

        a.deposit(3000, today.minusDays(3));
        a.deposit(250, today.minusDays(2));

        assertEquals(162.5, interestCalculator.calculateInterest(a), EPSILON);
    }

    @Test
    public void testCalculateInterestWithdraw() throws Exception {
        LastTenDaysNoWithdrawInterest interestCalculator = new LastTenDaysNoWithdrawInterest();

        AccountBase a = new MaxiSavingsAccount();
        LocalDateTime today = LocalDateTime.now();

        a.deposit(3000, today.minusDays(3));
        a.deposit(250, today.minusDays(2));
        a.withdraw(250, today.minusDays(2));

        assertEquals(3.0, interestCalculator.calculateInterest(a), EPSILON);
    }

    @Test
    public void testCalculateInterestNoWithdrawInTenDays() throws Exception {
        LastTenDaysNoWithdrawInterest interestCalculator = new LastTenDaysNoWithdrawInterest();

        AccountBase a = new MaxiSavingsAccount();
        LocalDateTime today = LocalDateTime.now();

        a.deposit(3000, today.minusDays(13));
        a.deposit(250, today.minusDays(12));
        a.withdraw(250, today.minusDays(12));

        assertEquals(150.0, interestCalculator.calculateInterest(a), EPSILON);
    }

}