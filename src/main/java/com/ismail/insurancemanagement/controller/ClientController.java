package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.model.Client;

public class ClientController {
    Client client;

    public void create(String firstName, String lastName , String email) {
        client = new Client(firstName, lastName, email);
    }

}
