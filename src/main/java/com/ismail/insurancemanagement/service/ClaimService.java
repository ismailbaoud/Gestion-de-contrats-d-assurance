package main.java.com.ismail.insurancemanagement.service;

import main.java.com.ismail.insurancemanagement.DAO.ClaimDAO;
import main.java.com.ismail.insurancemanagement.enums.ClaimType;
import main.java.com.ismail.insurancemanagement.model.Claim;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.util.*;

public class ClaimService {
    ClaimDAO claimDAO = new ClaimDAO();

    public boolean createClaim(ClaimType type,  Date date, Double amount , String description, UUID contractId) {
        Claim claim = new Claim(type, date, amount ,description);
        return claimDAO.createClaim(claim,contractId);
    }

    public boolean deleteClaim(UUID id) {
        return claimDAO.deleteClaim(id);
    }

    public Double calculeTotalCostOfClaimsForClient(UUID id) {
        return claimDAO.calculeTotalCostOfClaimsForClient(id);
    }

    //     search Claim By Id
    public Claim searchClaimById(UUID id) {
        return claimDAO.searchClaimById(id).orElse(null);
    }


    public List<Claim> displayForContract(UUID id) {
        return claimDAO.displayForContract(id);
    }


    public List<Claim> displaySortedByCost() {
        return claimDAO.displaySortedByCost();
    }


    public List<Claim> displayForClient(UUID id) {
        return claimDAO.displayForClient(id);
    }


    public List<Claim> displaysWithCostGreaterThanAmount(double amount) {
        return claimDAO.displaysWithCostGreaterThanAmount(amount);
    }

    public List<Claim> displayClaimsBeforeDate(Date date) {
        return claimDAO.displayClaimsBeforeDate(date);
    }
}
