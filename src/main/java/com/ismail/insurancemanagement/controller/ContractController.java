package main.java.com.ismail.insurancemanagement.controller;

import main.java.com.ismail.insurancemanagement.enums.ContractType;
import main.java.com.ismail.insurancemanagement.model.Contract;
import main.java.com.ismail.insurancemanagement.service.ContractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ContractController {
    public ContractService contractService = new ContractService();
    public boolean createContract(ContractType type , Date date , String clientId) {
        return contractService.craeteContract(type, date, clientId);
    }

    public Contract displayContractDetails(UUID id) {
        return contractService.displayContractDetails(id);
    }

    public boolean deleteContract(UUID id) {
        return contractService.deleteContract(id);
    }

    public ArrayList<Contract> displayClientContracts(UUID id) {
        return contractService.displayClientContracts(id);
    }
}
