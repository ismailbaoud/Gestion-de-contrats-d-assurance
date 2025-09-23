package main.java.com.ismail.insurancemanagement.DAO;

import main.java.com.ismail.insurancemanagement.config.ConnectionDB;
import main.java.com.ismail.insurancemanagement.model.Advisor;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class AdvisorDAO {

    Connection conn;

    public AdvisorDAO() {
        try {
            conn = ConnectionDB.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean create(Advisor advisor) {
        String sql = "INSERT INTO Advisor (id, firstname, lastname, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, advisor.getId());
            stmt.setString(2, advisor.getFirstName());
            stmt.setString(3, advisor.getLastName());
            stmt.setString(4, advisor.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM Advisor where id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Advisor findAdvisor(String id) {
        Advisor advisor;
        String sql = "SELECT * FROM Advisor where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
               advisor = new Advisor(resultSet.getString("firstname"), resultSet.getString("lastname"),resultSet.getString("email"));
               advisor.setId(UUID.fromString(resultSet.getString("id")));
               return  advisor;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Client> AdvisorClients(UUID id) {
        Client client;
        String sql = "SELECT * FROM Adviser_client where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ArrayList<Client> clients = new ArrayList<>();
            stmt.setObject(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                client = new Client(resultSet.getString("firstname"), resultSet.getString("lastname"),resultSet.getString("email"));
                client.setId((UUID) resultSet.getObject("id"));
                clients.add(client);
            }
            else {
                return null;
            }
            return clients;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
