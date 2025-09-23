package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.model.Client;
import main.java.com.ismail.insurancemanagement.service.ClientService;

import java.util.UUID;

public class ClientController {
    Client client;
    ClientService clientService = new ClientService();

    public boolean create(String firstName, String lastName , String email , String id) {
        return clientService.create(firstName, lastName, email,id);
    }

    public Client findClientById(String id) {
        return clientService.findClientById(id);
    }

    public Client findClientByLastName(String lastName) {
        return  clientService.findClientByLastName(lastName);
    }
}
