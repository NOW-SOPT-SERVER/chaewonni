
package org.example.service;

import org.example.classes.Account;
import org.example.classes.Bank;
import org.example.classes.Customer;
import org.example.exception.*;

public class BankService {
    private Bank bank;

    public BankService(Bank bank) {
        this.bank = bank;
    }

    // 계좌 생성 로직
    public Account createAccount(String customerName, String phoneNumber) throws DuplicateCustomerException {
        return bank.createAccount(customerName, phoneNumber);
    }

    // 고객 정보 조회 로직
    public Customer printCustomerInfo(String accountNumber) throws CustomerNotFoundException {
        return bank.findCustomerByAccountNumber(accountNumber);
    }

    // 잔액 조회 로직
    public double checkBalance(String accountNumber) throws AccountNotFoundException {
        Account account = bank.findAccount(accountNumber);
        return account.getBalance();
    }

    // 입금 로직
    public void deposit(String accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException {
        Account depositAccount = bank.findAccount(accountNumber);
        depositAccount.deposit(amount);
    }

    // 출금 로직
    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientFundsException {
        Account withdrawAccount = bank.findAccount(accountNumber);
        withdrawAccount.withdraw(amount);
    }

    // 계좌 이체 로직
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientFundsException {
        Account fromAccount = bank.findAccount(fromAccountNumber);
        Account toAccount = bank.findAccount(toAccountNumber);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}

