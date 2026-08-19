import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class JDBC {
    public static Connection getConnection() {
        // Register JDBC driver
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("SQL exception occurred while connecting to the SQL Server.");
            e.printStackTrace();

        }

        // Connect to the SQL Server
        var url = "jdbc:sqlserver://localhost:1433;databaseName=CompanyDB;encrypt=true;trustServerCertificate=true;";
        var user = "Thammasorn";
        var password = "Password01";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}