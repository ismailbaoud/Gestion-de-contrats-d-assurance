package main.java.com.ismail.insurancemanagement.model;

import main.java.com.ismail.insurancemanagement.enums.ClaimType;

import java.util.Date;

public class Claim {
    private ClaimType claimType = null;
    private Date dateDebut ;
    private Date dateFin ;
    private double montant ;
    private String description ;

    public Claim(ClaimType claimType, Date dateDebut, Date dateFin, double montant, String description) {
        this.claimType = claimType;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.description = description;
    }


    public ClaimType getClaimType() {
        return claimType;
    }

    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
