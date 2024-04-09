package org.example.classes;

import java.util.ArrayList;
import java.util.List;

//고객 생성, 고객에게 새로운 계좌 추가
public class Customer {
    private String name;
    private String phoneNumber;
    private List<Account> accounts;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
