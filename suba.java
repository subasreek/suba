import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ODBCExample {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load the ODBC driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // Connect to the database using the DSN
            String url = "jdbc:odbc:YourDSN";
            conn = DriverManager.getConnection(url);

            // Create a statement object to interact with the database
            stmt = conn.createStatement();

            // Execute a query
            String query = "SELECT * FROM your_table";
            rs = stmt.executeQuery(query);

            // Process the result set
            while (rs.next()) {
                // Example: Retrieve data by column name
                String columnData = rs.getString("your_column");
                System.out.println("Column Data: " + columnData);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC-ODBC Bridge driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL error occurred.");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
