package main.java.com.ismail.insurancemanagement.service;

import main.java.com.ismail.insurancemanagement.DAO.AdvisorDAO;
import main.java.com.ismail.insurancemanagement.model.Advisor;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.util.ArrayList;
import java.util.UUID;

public class AdvisorService {
    AdvisorDAO advisorDAO = new AdvisorDAO();
    Advisor advisor;

    public boolean create(String firstName , String lastName , String email ) {
        try {
            advisor = new Advisor(firstName,lastName,email);
            return advisorDAO.create(advisor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteAdvisor(String id) {

        return advisorDAO.delete(id);
    }

    public Advisor findAdvisor(String id) {
        return advisorDAO.findAdvisor(id);
    }

    public ArrayList<Client> AdvisorClients(UUID id) {
        return advisorDAO.AdvisorClients(id);
    }
}
