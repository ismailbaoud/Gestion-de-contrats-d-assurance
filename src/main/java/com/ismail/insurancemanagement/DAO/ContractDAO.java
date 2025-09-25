package main.java.com.ismail.insurancemanagement.DAO;

import main.java.com.ismail.insurancemanagement.config.ConnectionDB;
import main.java.com.ismail.insurancemanagement.enums.ContractType;
import main.java.com.ismail.insurancemanagement.model.Client;
import main.java.com.ismail.insurancemanagement.model.Contract;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class ContractDAO {
    Connection conn;

    public ContractDAO() {
        this.conn = ConnectionDB.getInstance().getConnection();
    }


    public boolean createContract(Contract contract) {
        String sql = "INSERT INTO Contract (id , type , startDate ,endDate , idClient) values (?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1 , contract.getId());
            stmt.setObject(2 , contract.getContractType().name());
            stmt.setObject(3 , contract.getDateDebut());
            stmt.setObject(4 , contract.getDateFin());
            stmt.setObject(5 , contract.getClient().getId());
            return stmt.executeUpdate() > 0;
        }catch (SQLException e) {
            System.out.println("you have err : " + e.getMessage());
            return false;
        }
    }


    public Optional<Contract> displayContractDetails(UUID id) {
        String sql = "SELECT c.*,cl.* from Contract c join Client cl ON c.idClient = cl.id";
        ArrayList<Contract> contracts = new ArrayList<>();
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ContractType type = ContractType.valueOf(rs.getString("c.type"));
                Contract contract = new Contract(type,(Date) rs.getObject("c.endDate"));
                contract.setDateDebut((Date) rs.getObject("c.startDate"));
                contract.setId(UUID.fromString(rs.getString("c.id")));
                Client client = new Client(rs.getString("cl.firstname"), rs.getString("cl.lastname"),rs.getString("cl.email"));
                client.setId(UUID.fromString(rs.getString("cl.id")));
                contract.setClient(client);
                contracts.add(contract);
            }
            Contract contract = contracts.stream().filter(a->a.getId().equals(id)).findFirst().orElse(null);
            if(contract != null){
                return Optional.of(contract);
            }else {
                return Optional.empty();
            }
        }catch (SQLException e) {
            System.out.println("you have err : "+ e.getMessage());
            return null;
        }
    }


    public boolean deleteContract(UUID id) {
        String sql = "DELETE FROM Contract where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, id);
            return stmt.executeUpdate() >0;
        }catch (SQLException e) {
            System.out.println("you have err when deleting : " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Contract> displayClientContracts(UUID id) {
        String sql = "SELECT c.* , cl.* FROM Contract c join Client cl on c.idClient = cl.id";
        ArrayList<Contract> contracts = new ArrayList<>();
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ContractType type =ContractType.valueOf(rs.getString("c.type"));
                Contract contract = new Contract(type,(Date) rs.getObject("c.endDate"));
                contract.setId(UUID.fromString(rs.getString("c.id")));
                contract.setDateDebut((Date) rs.getObject("c.startDate"));
                contracts.add(contract);
                Client client = new Client(rs.getString("cl.firstname"), rs.getString("cl.lastname"),rs.getString("cl.email"));
                client.setId(UUID.fromString(rs.getString("cl.id")));
                contract.setClient(client);
            }
            contracts.stream().filter(contact -> contact.getClient().equals(id));
            return contracts;
        }catch (SQLException e) {
            System.out.println("you have err : " + e.getMessage());
            return null;
        }
    }
}
