package main.java.com.ismail.insurancemanagement.view;

import java.util.Scanner;

public class ClientView {
    public Integer clientMenu() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("\n==== Client Management ====");
            System.out.println("1. Add a client (auto ID)");
            System.out.println("2. Show client by ID (Optional)");
            System.out.println("3. Show client by last name (Optional)");
            System.out.println("4. Delete a client by ID");
            System.out.println("5. List all clients (alphabetical by last name)");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");
            Integer choice = scanner.nextInt();
            return choice;
    }



}
