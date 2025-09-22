package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.model.Advisor;
import main.java.com.ismail.insurancemanagement.util.Helper;
import main.java.com.ismail.insurancemanagement.view.AdvisorView;
import main.java.com.ismail.insurancemanagement.view.ClientView;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.util.Scanner;
import java.util.UUID;

public class MainController {
    //view instantiation
    ClientView clientView = new ClientView();
    AdvisorView advisorView = new AdvisorView();
    //helper instantiation
    Helper helper = new Helper();
    /// System instantiation
    Scanner scanner = new Scanner(System.in);
    //Controllers instantiation
    ClientController clientController = new ClientController();
    AdvisorController advisorController = new AdvisorController();


    public MainController(Integer choice) {
        manageOptions(choice);
    }


    //manage main menu options
    public void manageOptions(Integer choice) {
        switch (choice) {
            case 1:
                advisorManagement();
                break;
            case 2:
                clientManagement();
                break;
            case 3:
                break;
            case 4:
                System.out.println("Good Bye !");
                break;
            default:
                System.out.println("Invalid choice, Try Again");
                break;
        }
    }

    //advisor//////////////////////

    //advisor management
    public void advisorManagement() {
        Integer choice;
        do {
            choice = advisorView.advisorMenu();
            switch (choice) {
                case 1:
                    createAdvisor();
                    helper.print("Advisor crated successfully");
                    break;
                case 2:
                    deleteAdvisor();
                    helper.print("the advisor has deleted successfully !");
                    break;
                case 3:
                    Advisor advisor = findAdvisor();
                    displayAdvisorDetails(advisor);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    helper.print("Good Bye !");
                    break;
                default:
                    helper.print("Invalid choice ! Try another choice...");
                    break;

            }
        } while (choice != 0);
    }

    //create advisor
    public boolean createAdvisor() {
        helper.print("PLease enter first name : ");
        String firstName = scanner.next();
        helper.print("Please enter last name : ");
        String lastName = scanner.next();
        helper.print("please enter email : ");
        String email = scanner.next();
        return advisorController.create(firstName, lastName, email);
    }

    //delete advisor by id
    public void deleteAdvisor() {
        try {

            helper.print("Please enter the Id of advisor : ");
            String idStr = scanner.next();
            UUID id = UUID.fromString(idStr);
            advisorController.deleteAdvisor(id);
        } catch (IllegalArgumentException e) {
            helper.print("Invalid UUID format. Please try again.");
        }
    }

    //find advisor by id

    public Advisor findAdvisor() {
        helper.print("Please enter the advisor Id :");
        String stId = scanner.next();
        UUID id = UUID.fromString(stId);
        return advisorController.findAdvisor(id);
    }

    public void displayAdvisorDetails(Advisor advisor) {
        helper.print("Id : " + advisor.getId()  + " ,First Name : " + advisor.getFirstName() + " , last Name : " + advisor.getLastName() + " ,Email : " + advisor.getEmail());
    }
    ///////////////////////////////
    //client management
    public void clientManagement() {
        Integer choice;
        do {
            choice = clientView.clientMenu();
            switch (choice){
                case 1:
                    createClient();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }while (choice != 0);

    }

    //create client
    public boolean createClient() {
            helper.print("PLease enter first name : ");
            String firstName = scanner.next();
            helper.print("Please enter last name : ");
            String lastName = scanner.next();
            helper.print("please enter email : ");
            String email = scanner.next();
            return advisorController.create(firstName,lastName,email);
        }
}
