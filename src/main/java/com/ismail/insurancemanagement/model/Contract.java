package main.java.com.ismail.insurancemanagement.model;

import main.java.com.ismail.insurancemanagement.enums.ContractType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class Contract {
    private ContractType contractType = null;
    private Date dateDebut ;
    private Date dateFin ;
    private HashMap<String, Claim> sinistes = new HashMap<String,Claim>();

    public Contract(ContractType contractType, Date dateDebut, Date dateFin, HashMap<String, Claim> sinistes) {
        this.contractType = contractType;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sinistes = sinistes;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
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

    public HashMap<String, Claim> getSinistes() {
        return sinistes;
    }

    public void setSinistes(HashMap<String, Claim> sinistes) {
        this.sinistes = sinistes;
    }
}
