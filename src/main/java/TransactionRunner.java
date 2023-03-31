import Data.UTILL.ConnectionManager;

import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionRunner {
    public static void main(String[] args) throws SQLException {
        String username = "postgres";
        String pass = "1234";
        String URL = "jdbc:postgresql://localhost:5433/postgres";
        Long flight_Id = 8l;
        ConnectionManager connectionManager = new ConnectionManager(URL, username, pass);
        var connection =   DriverManager.getConnection(URL, username, pass);
        //language=PostgreSQL
        String DELETE = """
                DELETE FROM flight
                where id = ?
                """;
        //language=PostgreSQL
        String DELETE_FROM_TICKET = """
                DELETE FROM ticket
                WHERE  flight_id = id
                """;
        try(connection) {
            var deleteFlightStatement =
                    connection.prepareStatement(DELETE);
            var deleteTicketStatement =
                    connection.prepareStatement(DELETE_FROM_TICKET);
            connection.setAutoCommit(false);
            connection.commit();
            {

                deleteFlightStatement.setLong(1, flight_Id);
                deleteFlightStatement.executeUpdate();
            }
        }


    }
}


