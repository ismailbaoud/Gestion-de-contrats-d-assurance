package main.java.com.ismail.insurancemanagement.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Person {


    private HashMap<String, Contract> contracts =new HashMap<String,Contract>();

    private Advisor advisor ;

    public Client(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public HashMap<String, Contract> getContracts() {
        return contracts;
    }

    public void setContracts(HashMap<String, Contract> contracts) {
        this.contracts = contracts;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
