package main.java.com.ismail.insurancemanagement.view;

import main.java.com.ismail.insurancemanagement.model.Contract;
import main.java.com.ismail.insurancemanagement.util.Helper;

import java.util.ArrayList;
import java.util.Scanner;

public class ContractView {
    Helper helper = new Helper();

    public Integer getContractMenu() {
        helper.print("1 => add contract");
        helper.print("2 => display contract information's");
        helper.print("3 => delete contract");
        helper.print("4 => display client contracts");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void displayContract(Contract contract) {
        helper.print("ID : "+contract.getId() + " , Type : " + contract.getContractType() +
                     " , StartDate : "+ contract.getDateDebut() + " , EndDate : " +contract.getDateFin() +
                     " , Client Name : "+ contract.getClient().getFirstName() + " " +contract.getClient().getLastName());
    }

    public void displayClientContracts(ArrayList<Contract> contracts) {
        for (Contract item : contracts) {
            System.out.println("id : " + item.getId() + " , type : " + item.getContractType() + " , started date : " + item.getDateDebut() + " , end date : " + item.getDateFin());
        }
    }
}
