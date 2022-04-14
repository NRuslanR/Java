package com.example;

public class Account {

    private int balance;
    
    public Account(int balance) throws AccountException
    {
        if (balance < 0)
            throw new AccountException("Balance less than zero");

        this.balance = balance;
    }

    public int withdraw(int amount) throws AccountException
    {
        if (amount < 0)
            throw new AccountException("Amount less than zero");

        if (balance < amount)
            throw new AccountException("Balance not enough to withdraw");

        balance -= amount;

        return balance;
    }

    public int getBalance() {
        return balance;
    }

}
