package org.example.classes;

import org.example.exception.AccountNotFoundException;
import org.example.exception.CustomerNotFoundException;
import org.example.exception.DuplicateCustomerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
    private List<Account> accounts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    //계좌번호 생성
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            int number = random.nextInt(10);
            accountNumber.append(number);
        }
        return accountNumber.toString();
    }

    //계좌 생성
    public Account createAccount(String customerName, String phoneNumber) throws DuplicateCustomerException {
        Customer customer = findCustomerByPhoneNumber(phoneNumber);
        if (customer != null && !customer.getName().equals(customerName)) {
            throw new DuplicateCustomerException("이미 다른 이름으로 등록되었습니다.");
        }
        if (customer == null) { //새 고객 생성
            customer = new Customer(customerName, phoneNumber);
            customers.add(customer);
        }
        Account account = new Account(generateAccountNumber());
        accounts.add(account);
        customer.addAccount(account);
        return account;
    }

    //전화번호로 고객 찾기
    private Customer findCustomerByPhoneNumber(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    //계좌 찾기
    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if(account.getAccountNum().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("계좌를 찾을 수 없습니다.");
    }

    //계좌 번호로 고객 정보 조회
    public Customer findCustomerByAccountNumber(String accountNumber) throws CustomerNotFoundException{
        for (Customer customer : customers) {
            List<Account> accounts = customer.getAccounts();
            for (Account account : accounts) {
                if(account.getAccountNum().equals(accountNumber)) {
                    return customer;
                }
            }
        }
        throw new CustomerNotFoundException("고객을 찾을 수 없습니다.");
    }
}