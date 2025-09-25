package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.model.Client;
import main.java.com.ismail.insurancemanagement.service.ClientService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class ClientController {
    Client client;
    ClientService clientService = new ClientService();

    //create client
    public boolean create(String firstName, String lastName , String email , String id) {
        return clientService.create(firstName, lastName, email,id);
    }

    //find client by id using optional for handling of null values
    public Client findClientById(String id) {
        return clientService.findClientById(id);
    }

    //find client by last name
    public ArrayList<Client> findClientByLastName(String lastName) {
        return  clientService.findClientByLastName(lastName);
    }

    //delete client
    public boolean deleteClient(String id) {
        return clientService.deleteClient(id);
    };

    //get all clients
    public ArrayList<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
