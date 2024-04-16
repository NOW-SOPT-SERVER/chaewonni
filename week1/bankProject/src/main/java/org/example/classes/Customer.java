package org.example.classes;

import java.util.ArrayList;
import java.util.List;

//고객 생성, 고객에게 새로운 계좌 추가
public class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
