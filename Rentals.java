import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

public class Rentals {
    private int rentalId;
    private int customerId;
    private int boatId;
    private int locationId;
    private Date date;

    public Rentals(int rentalId, int customerId, int boatId, int locationId, Date date) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.boatId = boatId;
        this.locationId = locationId;
        this.date = date;
    }

    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getBoatId() { return boatId; }
    public void setBoatId(int boatId) { this.boatId = boatId; }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    // CREATE
    public static void insertRental(int rental_id, int customer_id, int boat_id, int location_id, Date date) {
        String sql = """
            INSERT INTO Rentals (rental_id, customer_id, boat_id, location_id, date)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rental_id);
            stmt.setInt(2, customer_id);
            stmt.setInt(3, boat_id);
            stmt.setInt(4, location_id);
            stmt.setDate(5, date);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " rental inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public static void getAllRentals() {
        String sql = "SELECT * FROM Rentals";

        try (Connection conn = JDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("rental_id") + " | " +
                                rs.getInt("customer_id") + " | " +
                                rs.getInt("boat_id") + " | " +
                                rs.getInt("location_id") + " | " +
                                rs.getDate("date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE (example: update location_id by primary key)
    public static void updateRentalLocation(int rental_id, int newLocationId) {
        String sql = "UPDATE Rentals SET location_id = ? WHERE rental_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newLocationId);
            stmt.setInt(2, rental_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " rental updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteRental(int rental_id) {
        String sql = "DELETE FROM Rentals WHERE rental_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rental_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " rental deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
