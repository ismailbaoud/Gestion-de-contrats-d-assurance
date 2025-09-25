package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.enums.ContractType;
import main.java.com.ismail.insurancemanagement.model.Advisor;
import main.java.com.ismail.insurancemanagement.model.Client;
import main.java.com.ismail.insurancemanagement.model.Contract;
import main.java.com.ismail.insurancemanagement.util.Helper;
import main.java.com.ismail.insurancemanagement.view.AdvisorView;
import main.java.com.ismail.insurancemanagement.view.ClaimView;
import main.java.com.ismail.insurancemanagement.view.ClientView;
import main.java.com.ismail.insurancemanagement.view.ContractView;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;


public class MainController {
    //view instantiation
    ClientView clientView = new ClientView();
    AdvisorView advisorView = new AdvisorView();
    ContractView contractView = new ContractView();
    ClaimView claimView = new ClaimView();
    //helper instantiation
    Helper helper = new Helper();
    // System instantiation
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    //Controllers instantiation
    ClientController clientController = new ClientController();
    AdvisorController advisorController = new AdvisorController();
    ContractController contractController = new ContractController();


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
                contractManagement();
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
                    AdvisorClients();
                    helper.print("clients");
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
            String id = scanner.next();
            advisorController.deleteAdvisor(id);
        } catch (IllegalArgumentException e) {
            helper.print("Invalid UUID format. Please try again.");
        }
    }

    //find advisor by id

    public Advisor findAdvisor() {
        helper.print("Please enter the advisor Id :");
        String id = scanner.next();
        return advisorController.findAdvisor(id);

    }

    //find clients of advisor
    public ArrayList<Client> AdvisorClients() {
            try {
                helper.print("please enter the advisor id");
                String stId = scanner.next();
                UUID advId = UUID.fromString(stId);
                return  advisorController.AdvisorClients(advId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    //display the advisor details
    public void displayAdvisorDetails(Advisor advisor) {
        helper.print("Id : " + advisor.getId()  + " ,First Name : " + advisor.getFirstName() + " , last Name : " + advisor.getLastName() + " ,Email : " + advisor.getEmail());
    }

    //client management ////////////
    public void clientManagement() {
        Integer choice;
        do {
            choice = clientView.getClientMenu();
            switch (choice){
                case 1:
                    createClient();
                    System.out.println("Client created successfully");
                    break;
                case 2:
                    Client client = findClientById();
                    if (client != null) {
                        clientView.displayClient(client);
                    } else {
                        System.out.println("Client not found");
                    }                    break;
                case 3:
                    clientView.displayAllClients(findClientByLastName());
                    break;
                case 4:
                    if (deleteClient()) {
                        helper.print("Client deleted successfully !");
                    } else {
                        helper.print("Invalid Operation...There is no client withe this last name !!!");
                    }
                    break;
                case 5:
                    clientView.displayAllClients(getAllClients());
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
        helper.print("please enter the advisor Id : ");
        String id = scanner.next();
        return clientController.create(firstName,lastName,email,id);
    }


    //find client by id
    public Client findClientById() {
        helper.print("Please enter the client Id : ");
        String id = scanner.next();
        return clientController.findClientById(id);

    }

    //find client by last name
    public ArrayList<Client> findClientByLastName() {
        helper.print("Please enter the client last name : ");
        String lastName = scanner.next();
        ArrayList<Client> clients = clientController.findClientByLastName(lastName);
        return clients;
    }

    //delete Client by id
    public boolean deleteClient() {
        helper.print("Please enter the Client Id");
        String id = scanner.next();
        return  clientController.deleteClient(id);
    }

    //display all client
    public ArrayList<Client> getAllClients() {
        return clientController.getAllClients();
    }

    // Contract management
    public void contractManagement() {
        //        1 => add contract
        //        2 => display contract information's
        //        3 => delete contract
        //        4 => display client contracts
        Integer choice;
        do {
            choice = contractView.getContractMenu();
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    contractView.displayContract(displayContractDetails());
                    break;
                case 3:
                    if (deleteContract()) {
                        helper.print("Contract deleted with success");
                    } else {
                        helper.print("the contract not deleted");
                    }
                    break;
                case 4:
                    contractView.displayClientContracts(displayClientContrats());
                    break;
                case 0:
                    helper.print("Good bye !");
                    break;
                default:
                    break;
            }
        }while (choice != 0) ;
    }

    //create contract
    public void addContact() {
        try {
            helper.print("Please select the contract type :");
            helper.print("1 => AUTOMOBILE");
            helper.print("2 => REAL_ESTATE");
            helper.print("3 => HEALTH");
            helper.print("Enter your choice : ");
            ContractType contractType = null;
            Integer choice = scanner.nextInt();
            boolean condition = true;
            do {
                switch (choice) {
                    case 1:
                        condition = false;
                        contractType = ContractType.AUTOMOBILE;
                        break;
                    case 2:
                        condition = false;
                        contractType = ContractType.REAL_ESTATE;
                        break;
                    case 3:
                        condition = false;
                        contractType = ContractType.HEALTH;
                        break;
                    default:
                        break;
                }
            } while (condition);
            helper.print("Please enter the death line with format ( DD-MM-YYYY ): ");
            String deathline = scanner.next();
            Date date = formatter.parse(deathline);
            helper.print("Please enter the client id : ");
            String clientId = scanner.next();
            if (contractController.createContract(contractType, date ,clientId)) {
                System.out.println();
                helper.print("Contract added successfully ! ");
                System.out.println();
            }else {
                helper.print("there is error when created Contract ! ");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    //display contract details (find by id)
    public Contract displayContractDetails() {
        try {
            helper.print("Please enter the contract id");
            String id = scanner.next();
            UUID idUUID = UUID.fromString(id);
            return  contractController.displayContractDetails(idUUID);
        }catch (Exception e) {
            helper.print("There is err : " + e.getMessage());
            return null;
        }
    }

    //delete contract
    public boolean deleteContract() {
        helper.print("Please enter the Contract Id : ");
        UUID id = UUID.fromString(scanner.next());
        return  contractController.deleteContract(id);
    }

    //display the client contracts 
    public ArrayList<Contract> displayClientContrats() {
        helper.print("Please enter teh client id : ");
        UUID id = UUID.fromString(scanner.next());
        return contractController.displayClientContracts(id);
    }

    //claims management
    public void claimsManagement() {
        //        1. Add a claim
        //        2. Delete a claim by ID
        //        3. Calculate the total cost of claims for a client
        //        4. Search a claim by ID
        //        5. Display the list of claims for a contract
        //        6. Display the list of claims sorted by cost (descending)
        //        7. Display the list of claims for a client
        //        8. Display the list of claims before a given date
        //        9. Display the list of claims with cost greater than a given amount
        //        0. Exit
        int choice = claimView.getClaimsMenu();
        switch (choice) {
            case 1 :
                createClaim();
                break;
            case 2 :
//                deleteClaim();
                break;
            case 3 :
//                calculeTotalCostOfClaimsForClient();
                break;
            case 4 :
//                searchClaimById();
                break;
            case 5 :
//                displayForContract();
                break;
            case 6 :
//                displaySortedByCost();
                break;
            case 7 :
//                displayForClient();
                break;
            case 8 :
//                displaysWithCostGreaterThanAmount();
                break;
            case 9 :
                break;
        }
    }

    //create claim
    public void createClaim() {
        helper.print("Please enter the type of claim : ");
        helper.print("1 => CAR_ACCIDENT");
        helper.print("2 => HOUSE_ACCIDENT");
        helper.print("3 => ILLNESS");
        System.out.print("Enter your choice");
    }
}
