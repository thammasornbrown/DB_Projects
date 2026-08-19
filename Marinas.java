import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Marinas {
    private int locationId;
    private String locationName;
    private String address;

    public Marinas(int locationId, String locationName, String address) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.address = address;
    }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // CREATE
    public static void insertMarina(int location_id, String location_name, String address) {
        String sql = "INSERT INTO Marinas (location_id, location_name, address) VALUES (?, ?, ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, location_id);
            stmt.setString(2, location_name);
            stmt.setString(3, address);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " marina inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public static void getAllMarinas() {
        String sql = "SELECT * FROM Marinas";

        try (Connection conn = JDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("location_id") + " | " +
                                rs.getString("location_name") + " | " +
                                rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE (example: update location_name by primary key)
    public static void updateMarinaName(int location_id, String newLocationName) {
        String sql = "UPDATE Marinas SET location_name = ? WHERE location_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newLocationName);
            stmt.setInt(2, location_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " marina updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteMarina(int location_id) {
        String sql = "DELETE FROM Marinas WHERE location_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, location_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " marina deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
