package main.java.com.ismail.insurancemanagement.DAO;

import main.java.com.ismail.insurancemanagement.config.ConnectionDB;
import main.java.com.ismail.insurancemanagement.model.Advisor;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientDAO {
    Connection conn;

    public ClientDAO() {
        conn = ConnectionDB.getInstance().getConnection();
    }

    public boolean create(Client client , String id) {
        String sql = "INSERT INTO Client (id , firstname , lastname , email , idAdvisor) value (?, ?, ? ,? ,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, client.getId());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getLastName());
            stmt.setString(4, client.getEmail());
            stmt.setObject(5, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Client> findClientById(String id) {
        Client client;
        Advisor advisor;
        String sql = "Select c.* , a.* from Client c join Advisor a ON c.idAdvisor = a.id where c.id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getString("c.firstname"), rs.getString("c.lastname"), rs.getString("c.email"));
                client.setId(UUID.fromString(rs.getString("c.id")));
                advisor = new Advisor(rs.getString("a.firstname"),rs.getString("a.lastname"),rs.getString("a.email"));
                advisor.setId(UUID.fromString(rs.getString("a.id")));
                client.setAdvisor(advisor);
                return Optional.of(client);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Client> findClientByLastName(String lastName) {
        Client client;
        ArrayList<Client> clients = new ArrayList<Client>();
        String sql = "SELECT c.* , a.* from Client c join Advisor a ON c.idAdvisor = a.id";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Client(
                        rs.getString("c.firstname"), rs.getString("c.lastname"),rs.getString("c.email")
                );
                client.setId(UUID.fromString(rs.getString("c.id")));
                Advisor advisor = new Advisor(
                        rs.getString("a.firstname"), rs.getString("a.lastname") , rs.getString("a.email")
                );
                advisor.setId(UUID.fromString(rs.getString("a.id")));
                client.setAdvisor(advisor);
                clients.add(client);

            }else {
                return null;
            }
             clients = clients.stream().filter((a)->a.getLastName().equals(lastName)).collect(Collectors.toCollection(ArrayList::new ));
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteClient(String id) {
        String sql = "delete from Client where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0 ;
        }catch (SQLException e) {
            System.out.println("You have error :  " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Client> getAllClients() {
        String sql = "SELECT c.* , a.* from Client c join Advisor a on c.idAdvisor = a.id";
        ArrayList<Client> clients = new ArrayList<Client>();
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client(rs.getString("c.firstname"), rs.getString("c.lastname"),rs.getString("c.email"));
                    client.setId(UUID.fromString(rs.getString("c.id")));
                Advisor advisor = new Advisor(rs.getString("a.firstname"), rs.getString("a.lastname"), rs.getString("a.email"));
                    advisor.setId(UUID.fromString(rs.getString("a.id")));
                    client.setAdvisor(advisor);
                    clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
