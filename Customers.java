import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customers {
    private int customerId;
    private String name;
    private String email;
    private String phone;
    private String emergencyContact;



    public Customers(int customerId, String name, String email, String phone, String emergencyContact) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.emergencyContact = emergencyContact;
    }

    //Getters and setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    // CREATE
    public static void insertCustomer(int customer_id, String name, String email, int phone, int emergency_contact) {
        String sql = "INSERT INTO Customers (customer_id, name, email, phone, emergency_contact) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer_id);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setInt(4, phone);
            stmt.setInt(5, emergency_contact);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " Customer inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //READ
    public static void getAllCustomers() {
        String sql = "SELECT * FROM Customers";

        try (Connection conn = JDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("customer_id") + " | " +
                        rs.getString("name") + " | " + rs.getString("email") + " | " +
                        rs.getInt("phone") + " | " + rs.getInt("emergency_contact")  );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //UPDATE
    public static void updateCustomers(String customer_id, String newEmail) {
        String sql = "UPDATE Customers SET email = ? WHERE customer_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newEmail);
            stmt.setString(2, customer_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " student updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteCustomer(String customer_id) {
        String sql = "DELETE FROM Customers WHERE customer_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " student deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
