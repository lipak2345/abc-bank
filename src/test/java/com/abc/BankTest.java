package com.abc;

import com.abc.account.AccountBase;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        AccountBase checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccount() {
        Bank bank = new Bank();
        AccountBase savingsAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccount() {
        Bank bank = new Bank();
        AccountBase maxiSavingsAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    public void testTotalInterestPaid() throws Exception {
        Bank bank = new Bank();
        AccountBase maxiSavingsAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

        AccountBase savingsAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Ted").openAccount(savingsAccount));

        AccountBase checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Jill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(1500.0);
        maxiSavingsAccount.deposit(3000.0);

        assertEquals(172.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testGetFirstCustomer() throws Exception {
        Bank bank = new Bank();

        bank.addCustomer(new Customer("Bill"));
        bank.addCustomer(new Customer("Ted"));
        bank.addCustomer(new Customer("Wally"));

        assertEquals("Bill", bank.getFirstCustomer());
    }

    @Test
    public void testGetFirstCustomerNull() throws Exception {
        Bank bank = new Bank();

        assertNull(bank.getFirstCustomer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCustomerException() throws Exception {
        Bank bank = new Bank();

        bank.addCustomer(null);
    }
}
