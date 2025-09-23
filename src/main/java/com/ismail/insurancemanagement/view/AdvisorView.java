package main.java.com.ismail.insurancemanagement.view;

import main.java.com.ismail.insurancemanagement.util.Helper;

import java.util.Scanner;

public class AdvisorView {
    Helper helper = new Helper();
    Scanner scanner = new Scanner(System.in);
    public Integer advisorMenu() {
        helper.print("\n=== Advisor Management Menu ===");
        helper.print("1. Add an advisor");
        helper.print("2. Remove an advisor by ID");
        helper.print("3. Find an advisor by ID");
        helper.print("4. Show clients of an advisor by ID");
        helper.print("0. Exit");
        Integer choice = scanner.nextInt();
        return choice;
    }
}
