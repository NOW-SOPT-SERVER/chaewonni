package org.example.view;

import org.example.classes.Account;
import org.example.classes.Customer;
import org.example.exception.*;
import org.example.service.BankService;

import java.util.Scanner;

public class BankView {
    private BankService bankService;
    private Scanner scanner;

    public BankView(BankService bankService) {
        this.bankService = bankService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n환영합니다. 원하시는 서비스를 선택하세요: 1.계좌 생성  2.고객 정보 조회  3.잔액 조회  4.입금  5.출금  6.계좌 이체  7.종료");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleCreateAccount();
                    break;
                case 2:
                    handlePrintCustomerInfo();
                    break;
                case 3:
                    handleCheckBalance();
                    break;
                case 4:
                    handleDeposit();
                    break;
                case 5:
                    handleWithdraw();
                    break;
                case 6:
                    handleTransfer();
                    break;
                case 7:
                    System.out.println("은행 프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택해주세요.");
            }
        }
    }

    private void handleCreateAccount() {
        try{
            System.out.println("\n계좌 생성하기");
            System.out.print("고객 이름을 입력하세요: ");
            String name = scanner.nextLine();
            System.out.print("전화번호를 입력하세요: ");
            String phoneNumber = scanner.nextLine();
            Account account = bankService.createAccount(name, phoneNumber);
            System.out.println("계좌 생성 완료, 계좌 번호: " + account.getAccountNum());
        } catch (DuplicateCustomerException e) {
            System.out.println("계좌 생성 실패: " + e.getMessage());
        }
    }

    private void handlePrintCustomerInfo() {
        try{
            System.out.println("\n계좌 번호로 고객 정보 조회하기");
            System.out.print("계좌 번호를 입력하세요: ");
            String accountNum = scanner.nextLine();

            Customer customer = bankService.printCustomerInfo(accountNum);
            System.out.println("계좌 번호 " + accountNum + "의 고객 정보\n" +
                    "고객 이름: " + customer.getName() + "\n" +
                    "고객 전화번호: " + customer.getPhoneNumber());
        } catch (CustomerNotFoundException e) {
            System.out.println("고객 정보 조회 실패: " + e.getMessage());
        }
    }

    private void handleCheckBalance() {
        try{
            System.out.println("\n잔액 조회하기");
            System.out.print("잔액을 조회할 계좌 번호를 입력하세요: ");
            String accountNum = scanner.nextLine();
            double balance = bankService.checkBalance(accountNum);
            System.out.println(accountNum + "의 잔액은 " + balance + "원 입니다.");
        } catch (AccountNotFoundException e) {
            System.out.println("잔액 조회 실패: " + e.getMessage());
        }
    }

    private void handleDeposit() {
        try{
            System.out.println("\n입금하기");
            System.out.print("입금할 계좌 번호를 입력하세요: ");
            String accountNum = scanner.nextLine();
            System.out.print("입금할 금액을 입력하세요: ");
            double amount = scanner.nextDouble();
            bankService.deposit(accountNum, amount);
            System.out.println(amount + "원 입금 완료");
        } catch (InvalidAmountException | AccountNotFoundException e) {
            System.out.println("입금 실패: " + e.getMessage());
        }
    }

    private void handleWithdraw() {
        try{
            System.out.println("\n출금하기");
            System.out.print("출금할 계좌 번호를 입력하세요: ");
            String accountNum = scanner.nextLine();
            System.out.print("출금할 금액을 입력하세요: ");
            double amount = scanner.nextDouble();
            bankService.withdraw(accountNum, amount);
            System.out.println(amount + "원 출금 완료");
        } catch (InvalidAmountException | InsufficientFundsException | AccountNotFoundException e) {
            System.out.println("출금 실패: " + e.getMessage());
        }
    }

    private void handleTransfer() {
        try{
            System.out.println("\n계좌 이체하기");
            System.out.print("출금할 계좌 번호를 입력하세요: ");
            String fromAccountNum = scanner.nextLine();
            System.out.print("입금할 계좌 번호를 입력하세요: ");
            String toAccountNum = scanner.nextLine();
            System.out.print("이체할 금액을 입력하세요: ");
            double amount = scanner.nextDouble();
            bankService.transfer(fromAccountNum, toAccountNum, amount);
            System.out.println("이체 성공: " + amount + "원이 " + fromAccountNum + "에서 " + toAccountNum + "으로 이체되었습니다.");
        } catch (InvalidAmountException | InsufficientFundsException | AccountNotFoundException e) {
            System.out.println("이체 실패: " + e.getMessage());
        }
    }
}
