package main.java.com.ismail.insurancemanagement.DAO;

import main.java.com.ismail.insurancemanagement.config.ConnectionDB;
import main.java.com.ismail.insurancemanagement.enums.ClaimType;
import main.java.com.ismail.insurancemanagement.model.Claim;
import main.java.com.ismail.insurancemanagement.model.Client;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClaimDAO {

    private final Connection conn;

    public ClaimDAO() {
        conn = ConnectionDB.getInstance().getConnection();
    }

    // ========================= CREATE ================================
    public boolean createClaim(Claim claim, UUID contractId) {
        String sql = "INSERT INTO Claim (id ,type , amount , description , startDate , endDate, idContract) " +
                "VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, claim.getId().toString());
            stmt.setString(2, claim.getClaimType().name());
            stmt.setDouble(3, claim.getMontant());
            stmt.setString(4, claim.getDescription());
            stmt.setObject(5, claim.getDateDebut());
            stmt.setObject(6, claim.getDateFin());
            stmt.setObject(7, contractId.toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error in createClaim: " + e.getMessage());
        }
        return false;
    }

    // ========================= DELETE ================================
    public boolean deleteClaim(UUID id) {
        String sql = "DELETE FROM Claim WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id.toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error in deleteClaim: " + e.getMessage());
        }
        return false;
    }

    // ========================= SEARCH BY ID ==========================
    public Optional<Claim> searchClaimById(UUID id) {
        String sql = "SELECT * FROM Claim WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToClaim(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL error in searchClaimById: " + e.getMessage());
        }
        return Optional.empty();
    }


    // ========================= TOTAL COST ============================
    public double calculeTotalCostOfClaimsForClient(UUID clientId) {
        String sql = "SELECT c.*" +
                "FROM Claim c " +
                "JOIN Contract ct ON c.idContract = ct.id where ct.idClient = ?";

        ArrayList<Claim> claims = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                claims.add(mapResultSetToClaim(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL error in calculeTotalCostOfClaimsForClient: " + e.getMessage());
            return 0.0;
        }

        return claims.stream()
                .mapToDouble(Claim::getMontant)
                .sum();
    }


    // ========================= DISPLAY FOR CONTRACT ==================
    public List<Claim> displayForContract(UUID contractId) {
        String sql = "SELECT * FROM Claim WHERE idContract = ?";
        List<Claim> claims = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, contractId.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                claims.add(mapResultSetToClaim(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL error in displayForContract: " + e.getMessage());
        }
        return claims;
    }



//    // ========================= DISPLAY SORTED ========================
    public List<Claim> displaySortedByCost() {
        String sql = "SELECT * FROM Claim ORDER BY amount DESC";
        List<Claim> claims = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                claims.add(mapResultSetToClaim(rs));

            }
        } catch (SQLException e) {
            System.out.println("SQL error in displaySortedByCost: " + e.getMessage());
        }
        return claims;
    }



    // ========================= DISPLAY FOR CLIENT ====================
    public List<Claim> displayForClient(UUID clientId) {
        String sql = "SELECT c.* FROM Claim c " +
                "JOIN Contract ct ON c.idContract = ct.id " +
                "WHERE ct.idClient = ?";
        List<Claim> claims = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, clientId.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                claims.add(mapResultSetToClaim(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL error in displayForClient: " + e.getMessage());
        }
        return claims;
    }

//    // ========================= COST GREATER ==========================
    public List<Claim> displaysWithCostGreaterThanAmount(double amount) {
        String sql = "SELECT * FROM Claim WHERE amount > ?";
        List<Claim> claims = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                claims.add(mapResultSetToClaim(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL error in displaysWithCostGreaterThanAmount: " + e.getMessage());
        }
        return claims;
    }

//    // ========================= UTILITY ===============================
private Claim mapResultSetToClaim(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        ClaimType type = ClaimType.valueOf(rs.getString("type"));
        double amount = rs.getDouble("amount");
        String description = rs.getString("description");
        Date startDate = rs.getDate("startDate");
        Date endDate = rs.getDate("endDate");
        Claim claim = new Claim(type, endDate, amount, description);
        claim.setId(id);
        claim.setDateDebut(startDate);
        return claim;
    }

    // ========================= DISPLAY CLAIMS BEFORE DATE ==================
    public List<Claim> displayClaimsBeforeDate(Date date) {
        String sql = "SELECT c.*, ct.idClient " +
                "FROM Claim c " +
                "JOIN Contract ct ON c.idContract = ct.id";

        List<Claim> claims = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Claim claim = mapResultSetToClaim(rs);
                claims.add(claim);
            }
        } catch (SQLException e) {
            System.out.println("SQL error in displayClaimsBeforeDate: " + e.getMessage());
            return new ArrayList<>();
        }

        return claims.stream()
                .filter(claim -> claim.getDateDebut().before(date))
                .collect(Collectors.toList());

    }


}
