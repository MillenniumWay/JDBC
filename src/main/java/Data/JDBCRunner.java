package Data;

import Data.UTILL.ConnectionManager;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCRunner {
    public static void main(String[] args) throws SQLException {
        String username = "postgres";
        String pass = "1234";
        String URL = "jdbc:postgresql://localhost:5433/postgres";
        ConnectionManager connectionManager = new ConnectionManager(URL, username, pass);
        var connection =   DriverManager.getConnection(URL, username, pass);
        //language=PostgreSQL

        String SQL = """
               CREATE TABLE info (
                id serial primary key,
                data varchar(23)
               );
                """;

        //language=PostgreSQL
        String insert = """
                INSERT INTO info(data) values 
                ('Test 1'),
                ('Test 1'),
                ('Test 1'),
                ('Test 1'),
                ('Test 1')
                """;

        //language=PostgreSQL
        String select = """
                SELECT * FROM info;
                """;

        try (connection) {
         System.out.println(connection.getTransactionIsolation());
            var statment = connection.createStatement();
            var result = statment.execute(select);
            var query = statment.executeQuery(select);
            while (query.next()) {
                System.out.println(query.getLong("id"));
                System.out.println(query.getString("data"));
                System.out.println("--------------------");
            }
     }


    }
}
