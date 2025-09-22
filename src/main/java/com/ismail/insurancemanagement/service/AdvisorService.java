package main.java.com.ismail.insurancemanagement.service;

import main.java.com.ismail.insurancemanagement.DAO.AdvisorDAO;
import main.java.com.ismail.insurancemanagement.model.Advisor;

import java.util.UUID;

public class AdvisorService {
    AdvisorDAO advisorDAO = new AdvisorDAO();
    Advisor advisor;

    public boolean create(String firstName , String lastName , String email) {
        try {
            advisor = new Advisor(firstName,lastName,email);
            return advisorDAO.create(advisor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteAdvisor(UUID id) {

        return advisorDAO.delete(id);
    }

    public Advisor findAdvisor(UUID id) {
        return advisorDAO.findAdvisor(id);
    }
}
