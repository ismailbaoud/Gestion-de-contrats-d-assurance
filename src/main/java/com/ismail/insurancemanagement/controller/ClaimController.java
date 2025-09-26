package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.enums.ClaimType;
import main.java.com.ismail.insurancemanagement.model.Claim;
import main.java.com.ismail.insurancemanagement.service.ClaimService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ClaimController {
    ClaimService claimService = new ClaimService();

    public boolean createClaim(ClaimType type , Double amount ,Date endDate , String description, UUID contractId) {
        return claimService.createClaim(type,endDate,amount,description, contractId);
    }


    public boolean deleteClaim(UUID id) {
        return claimService.deleteClaim(id);
    }

    public Double calculeTotalCostOfClaimsForClient(UUID id) {
        return claimService.calculeTotalCostOfClaimsForClient(id);
    }

    public Claim searchClaimById(UUID id) {
        return claimService.searchClaimById(id);
    }


    public List<Claim> displayForContract(UUID id) {
        return claimService.displayForContract(id);
    }


    public List<Claim> displaySortedByCost() {
        return claimService.displaySortedByCost();
    }


    public List<Claim> displayForClient(UUID id) {
        return claimService.displayForClient(id);
    }


    public List<Claim> displaysWithCostGreaterThanAmount(Double amount) {
        return claimService.displaysWithCostGreaterThanAmount(amount);
    }

    public List<Claim> displayClaimsBeforeDate(Date date) {
        return claimService.displayClaimsBeforeDate(date);
    }
}
