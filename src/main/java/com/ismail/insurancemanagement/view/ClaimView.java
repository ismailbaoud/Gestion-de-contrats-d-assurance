package main.java.com.ismail.insurancemanagement.view;

import main.java.com.ismail.insurancemanagement.model.Claim;
import main.java.com.ismail.insurancemanagement.util.Helper;

import java.util.List;
import java.util.Scanner;

public class ClaimView {

    Helper helper = new Helper();
    Scanner scanner = new Scanner(System.in);
    public int getClaimsMenu() {
        helper.print("\n===== MENU - CLAIMS MANAGEMENT =====");
        helper.print("1. Add a claim");
        helper.print("2. Delete a claim by ID");
        helper.print("3. Calculate the total cost of claims for a client");
        helper.print("4. Search a claim by ID");
        helper.print("5. Display the list of claims for a contract");
        helper.print("6. Display the list of claims sorted by cost (descending)");
        helper.print("7. Display the list of claims for a client");
        helper.print("8. Display the list of claims before a given date");
        helper.print("9. Display the list of claims with cost greater than a given amount");
        helper.print("10. Exit");
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }


    public void displayClaim(Claim claim) {
        helper.print("id : "+ claim.getId() +  " , start date : "+ claim.getDateDebut()+ " , end date : "+ claim.getDateFin()+
                " , type : "+ claim.getClaimType() + " , description : "+claim.getDescription() +
                " , amount : "+claim.getMontant());
    }


    public void displayClaims(List<Claim> claims) {
        for (Claim claim : claims) {
            helper.print("id : "+ claim.getId() +  " , start date : "+ claim.getDateDebut()+ " , end date : "+ claim.getDateFin()+
                    " , type : "+ claim.getClaimType() + " , description : "+claim.getDescription() +
                    " , amount : "+claim.getMontant());
        }
    }

}
