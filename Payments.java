import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payments {
    private int paymentId;
    private int rentalId;
    private LocalDateTime paymentDatetime;
    private BigDecimal amount;
    private String method;
    private String status;

    public Payments(int paymentId, int rentalId, LocalDateTime paymentDatetime, BigDecimal amount, String method, String status) {
        this.paymentId = paymentId;
        this.rentalId = rentalId;
        this.paymentDatetime = paymentDatetime;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }

    public LocalDateTime getPaymentDatetime() { return paymentDatetime; }
    public void setPaymentDatetime(LocalDateTime paymentDatetime) { this.paymentDatetime = paymentDatetime; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // CREATE
    public static void insertPayment(int payment_id, int rental_id, LocalDateTime payment_datetime,
                                     BigDecimal amount, String method, String status) {

        String sql = """
            INSERT INTO Payments (payment_id, rental_id, payment_datetime, amount, method, status)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment_id);
            stmt.setInt(2, rental_id);
            stmt.setObject(3, payment_datetime); // works for SQL Server datetime

            stmt.setBigDecimal(4, amount);
            stmt.setString(5, method);
            stmt.setString(6, status);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " payment inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public static void getAllPayments() {
        String sql = "SELECT * FROM Payments";

        try (Connection conn = JDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("payment_id") + " | " +
                                rs.getInt("rental_id") + " | " +
                                rs.getObject("payment_datetime") + " | " +
                                rs.getBigDecimal("amount") + " | " +
                                rs.getString("method") + " | " +
                                rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE (example: update status by primary key)
    public static void updatePaymentStatus(int payment_id, String newStatus) {
        String sql = "UPDATE Payments SET status = ? WHERE payment_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, payment_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " payment updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deletePayment(int payment_id) {
        String sql = "DELETE FROM Payments WHERE payment_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment_id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " payment deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
