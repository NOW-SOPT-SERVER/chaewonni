package org.example;

import org.example.classes.Bank;
import org.example.service.BankService;
import org.example.view.BankView;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        BankService bankService = new BankService(bank);
        BankView bankView = new BankView(bankService);
        bankView.run();
    }
}