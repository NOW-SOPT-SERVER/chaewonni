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

    //전화번호로 고객 찾기
    public Customer findCustomerByPhoneNumber(String phoneNumber) throws CustomerNotFoundException{
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

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}