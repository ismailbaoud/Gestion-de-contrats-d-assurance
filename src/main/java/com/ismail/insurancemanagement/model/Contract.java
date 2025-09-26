package main.java.com.ismail.insurancemanagement.model;

import main.java.com.ismail.insurancemanagement.enums.ContractType;

import java.util.Date;
import java.util.UUID;

public class Contract {
    private ContractType contractType = null;
    private Date dateDebut ;
    private Date dateFin ;
    private UUID id ;

    private Client client;

    

    public Contract(ContractType contractType, Date dateFin) {
        this.contractType = contractType;
        this.dateDebut = new Date();
        this.dateFin = dateFin;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
