package Data;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCRunner {
    public static void main(String[] args) throws SQLException {
     String username = "postgres";
     String pass = "1234";

     String URL = "jdbc:postgresql://localhost:5433/postgres";
     DriverManager.getConnection(URL, username, pass);


    }
}
