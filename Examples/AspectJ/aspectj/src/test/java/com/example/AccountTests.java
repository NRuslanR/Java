package com.example;

import org.junit.Assert;
import org.junit.Test;

public class AccountTests {

    @Test(expected = Test.None.class)
    public void shouldAccountHasNonNegativeBalance() throws AccountException {
        Account account1 = new Account(0),
                account2 = new Account(1);

        Assert.assertArrayEquals(
                new int[] {
                        account1.getBalance(),
                        account2.getBalance()
                },
                new int[] {
                        0,
                        1
                });

    }

    @Test(expected = AccountException.class)
    public void shouldAccountRaiseError_When_BalanceNegative() throws AccountException {
        new Account(-1);
    }

    @Test
    public void shouldAccountWithDraw_When_BalanceEnough_And_AmountIsCorrect() throws AccountException
    {
        Account account = new Account(50);

        Assert.assertEquals(account.withdraw(10), 40);
    }

    @Test(expected = AccountException.class)
    public void shouldNotAccountWithDraw_When_BalanceNotEnough() throws AccountException
    {
        Account account = new Account(50);

        account.withdraw(60);
    }

    @Test(expected = AccountException.class)
    public void shouldNotAccountWithDraw_When_AmountNotCorrect() throws AccountException
    {
        Account account = new Account(50);

        account.withdraw(-1);
    }
}