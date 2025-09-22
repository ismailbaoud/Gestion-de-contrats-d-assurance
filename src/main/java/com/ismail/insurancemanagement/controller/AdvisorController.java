package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.model.Advisor;
import main.java.com.ismail.insurancemanagement.service.AdvisorService;
import main.java.com.ismail.insurancemanagement.util.Helper;

import java.util.UUID;

public class AdvisorController {
    AdvisorService advisorService = new AdvisorService();

    public boolean create(String firstName , String lastName , String email) {
        try {
            return advisorService.create(firstName,lastName,email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteAdvisor(UUID id) {
        return advisorService.deleteAdvisor(id);
    }

    public Advisor findAdvisor(UUID id) {
        return  advisorService.findAdvisor(id);
    }
}
