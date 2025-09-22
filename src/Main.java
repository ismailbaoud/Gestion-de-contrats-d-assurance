
import main.java.com.ismail.insurancemanagement.controller.ContractController;
import main.java.com.ismail.insurancemanagement.controller.MainController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer choice;
        do {
            System.out.println("1 => Advisors Management");
            System.out.println("1 => Clients management");
            System.out.println("2 => Contracts management");
            System.out.println("3 => Claims management ");
            System.out.println("4 => Quite");
            choice = scanner.nextInt();

            new MainController(choice);
        }while (choice != 4);
    }
}