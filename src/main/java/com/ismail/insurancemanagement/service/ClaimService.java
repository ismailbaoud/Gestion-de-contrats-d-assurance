package main.java.com.ismail.insurancemanagement.service;

import main.java.com.ismail.insurancemanagement.DAO.ClaimDAO;
import main.java.com.ismail.insurancemanagement.enums.ClaimType;
import main.java.com.ismail.insurancemanagement.model.Claim;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.util.*;
import java.util.stream.Collectors;

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
        return claimDAO.calculeTotalCostOfClaimsForClient(id).stream()
                .mapToDouble(Claim::getMontant)
                .sum();
    }

    //     search Claim By Id
    public Claim searchClaimById(UUID id) {
        return claimDAO.searchClaimById(id).orElse(null);
    }


    public List<Claim> displayForContract(UUID id) {
        return claimDAO.displayForContract(id).stream()
                .filter(item -> item.getContract().getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public List<Claim> displaySortedByCost() {
        return claimDAO.displaySortedByCost().stream()
                .sorted(Comparator.comparingDouble(Claim::getMontant).reversed())
                .toList();
    }


    public List<Claim> displayForClient(UUID id) {
        return claimDAO.displayForClient(id).stream()
                .filter(a -> a.getContract().getClient().getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public List<Claim> displaysWithCostGreaterThanAmount(double amount) {
        return claimDAO.displaysWithCostGreaterThanAmount(amount).stream()
                .filter(a -> a.getMontant() > amount)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Claim> displayClaimsBeforeDate(Date date) {
        return claimDAO.displayClaimsBeforeDate(date).stream()
                .filter(claim -> claim.getDateDebut().before(date))
                .collect(Collectors.toList());
    }
}
