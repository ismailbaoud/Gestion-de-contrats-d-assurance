package main.java.com.ismail.insurancemanagement.service;

import main.java.com.ismail.insurancemanagement.DAO.ClientDAO;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.util.UUID;

public class ClientService {
    ClientDAO clientDAO = new ClientDAO();
    public boolean create(String firstName , String lastName , String email , String id) {
        Client client = new Client(firstName, lastName, email);
        return clientDAO.create(client, id);
    }

    public Client findClientById(String id) {
        return clientDAO.findClientById(id);
    }

    public Client findClientByLastName(String lastName) {
        return clientDAO.findClientByLastName(lastName);
    }
}
