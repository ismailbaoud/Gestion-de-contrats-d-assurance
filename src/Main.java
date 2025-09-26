import main.java.com.ismail.insurancemanagement.controller.MainController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer choice = -1; // start with something not 0
        do {
            try {
                System.out.println("1 => Advisors Management");
                System.out.println("2 => Clients management");
                System.out.println("3 => Contracts management");
                System.out.println("4 => Claims management");
                System.out.println("0 => Quit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();

                new MainController(choice);

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
                choice = -1;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);

        scanner.close();
    }
}
