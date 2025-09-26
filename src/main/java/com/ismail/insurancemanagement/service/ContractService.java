package main.java.com.ismail.insurancemanagement.service;

import main.java.com.ismail.insurancemanagement.DAO.ContractDAO;
import main.java.com.ismail.insurancemanagement.enums.ContractType;
import main.java.com.ismail.insurancemanagement.model.Client;
import main.java.com.ismail.insurancemanagement.model.Contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class ContractService {
    ContractDAO contractDAO = new ContractDAO();
    ClientService clientService = new ClientService();
    public boolean craeteContract(ContractType cType, Date date, String clientId) {
        Client client = clientService.findClientById(clientId);

        Contract contract = new Contract(cType, date);
        contract.setClient(client);
            return contractDAO.createContract(contract);
    }


    public Contract displayContractDetails(UUID id) {
        return contractDAO.displayContractDetails(id).orElse(null);
    }

    public boolean deleteContract(UUID id) {
        return contractDAO.deleteContract(id);
    }

    public ArrayList<Contract> displayClientContracts(UUID id) {
        return contractDAO.displayClientContracts(id);
    }

}
