package frontend;

import backend.Exercise2;
import entity.Account;

public class Program2 {
    public static void main(String[] args) {
        Exercise2 exercise = new Exercise2();
        Account[] accounts = exercise.question1InitAccounts();
        for (Account account : accounts) {
            System.out.println(account.email + " | " + account.userName + " | " + account.fullName + " | " + account.createDate);
        }
    }
}

