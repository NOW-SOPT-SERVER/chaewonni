
package org.example.service;

import org.example.classes.Account;
import org.example.classes.Bank;
import org.example.classes.Customer;
import org.example.exception.*;

import java.util.List;
import java.util.Random;

public class BankService {
    private Bank bank;

    public BankService(Bank bank) {
        this.bank = bank;
    }

    //계좌번호 생성
    public String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            int number = random.nextInt(10);
            accountNumber.append(number);
        }
        return accountNumber.toString();
    }

    // 계좌 생성 로직
    public Account createAccount(String customerName, String phoneNumber) throws DuplicateCustomerException, CustomerNotFoundException {
        Customer customer = bank.findCustomerByPhoneNumber(phoneNumber);
        if (customer != null && !customer.getName().equals(customerName)) {
            throw new DuplicateCustomerException("이미 다른 이름으로 등록되었습니다.");
        }
        if (customer == null) { //새 고객 생성
            customer = new Customer(customerName, phoneNumber);
            bank.addCustomer(customer);
        }
        Account account = new Account(generateAccountNumber());
        bank.addAccount(account);
        customer.addAccount(account);
        return account;
    }

    //고객 정보 조회 로직
    public Customer printCustomerInfo(String accountNumber) throws CustomerNotFoundException, AccountNotFoundException {
        Account account = bank.findAccount(accountNumber);
        for (Customer customer : bank.getCustomers()) {
            List<Account> accounts = customer.getAccounts();
            if (accounts.contains(account)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("고객을 찾을 수 없습니다.");
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

