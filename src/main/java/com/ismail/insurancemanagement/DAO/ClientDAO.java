package main.java.com.ismail.insurancemanagement.DAO;

import main.java.com.ismail.insurancemanagement.config.ConnectionDB;
import main.java.com.ismail.insurancemanagement.model.Advisor;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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

    public Client findClientById(String id) {
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
                return client;
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Client findClientByLastName(String lastName) {
        Client client;
        String sql = "SELECT c.* a.* from Client joint Advisor on c.lastname = a.lastname where c.lastname = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, lastName);
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
                return client;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
