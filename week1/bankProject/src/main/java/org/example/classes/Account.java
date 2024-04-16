package org.example.classes;

import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidAmountException;

public class Account {
    private String accountNum;
    private double balance;
    private Customer owner;

    public Account(String accountNum, Customer owner) {
        this.accountNum = accountNum;
        this.balance = 0;
        this.owner = owner;
    }

    //입금
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("입금 금액은 0보다 커야 합니다.");
        }
        balance += amount;
    }

    //출금
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("출금 금액은 0보다 커야 합니다.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("잔액 부족으로 출금할 수 없습니다.");
        }
        balance -= amount;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }
}
