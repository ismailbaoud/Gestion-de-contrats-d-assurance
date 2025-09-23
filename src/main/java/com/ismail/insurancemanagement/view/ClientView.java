package main.java.com.ismail.insurancemanagement.view;

import main.java.com.ismail.insurancemanagement.model.Client;
import main.java.com.ismail.insurancemanagement.util.Helper;

import java.util.Scanner;

public class ClientView {
    Helper helper = new Helper();
    public Integer clientMenu() {
        Scanner scanner = new Scanner(System.in);
            helper.print("\n==== Client Management ====");
            helper.print("1. Add a client");
            helper.print("2. Show client by ID");
            helper.print("3. Show client by last name");
            helper.print("4. Delete a client by ID");
            helper.print("5. List all clients (alphabetical by last name)");
            helper.print("0. Back to Main Menu");
            helper.print("Choice: ");
            Integer choice = scanner.nextInt();
            return choice;
    }

    public void displayClient(Client client) {
        helper.print("Client info : \nUUID : " + client.getId() +
                " ,First Name : " + client.getFirstName() +
                " ,LastName : " + client.getLastName() +
                " ,Email : " + client.getEmail() );

        helper.print("His Advisor info : \nFirst Name : " + client.getAdvisor().getFirstName() +
                " ,Last Name : " + client.getAdvisor().getLastName() +
                " ,Email: " + client.getAdvisor().getEmail());
    }



}
