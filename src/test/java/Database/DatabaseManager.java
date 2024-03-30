package Database;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;
    private Statement statement;

    public DatabaseManager() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/Database/hotelDetails.db");
        statement = connection.createStatement();
        System.out.println("Opened database successfully");
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS hotelDetails" +
                "(ID INTEGER PRIMARY KEY," +
                " username TEXT, password TEXT, Location TEXT, Hotel TEXT, RoomType TEXT, NumberOfRooms INTEGER, CheckInDate TEXT, CheckOutDate TEXT, AdultsPerRoom INTEGER, ChildrenPerRoom TEXT, FirstName TEXT, LastName TEXT, BillingAddress TEXT, CreditCardNo TEXT, CreditCardType TEXT, ExpiryMonth TEXT, ExpiryYear INTEGER, CVVNumber INTEGER)";
        statement.executeUpdate(sql);
        System.out.println("Table created successfully");
    }

    public void insertData() throws SQLException {
        String sql = "INSERT INTO hotelDetails (username, password, Location, Hotel, RoomType, NumberOfRooms, CheckInDate, CheckOutDate, AdultsPerRoom, ChildrenPerRoom, FirstName, LastName, BillingAddress, CreditCardNo, CreditCardType, ExpiryMonth, ExpiryYear, CVVNumber) " +
                "VALUES ('SithandiweZuma', '@Anele123', 'Paris', 'Hotel Creek', 'Double', 2, '01/04/2024', '03/04/2024', 1, 'None', 'Sithandiwe', 'Zuma', '27 Silwood', '5327480158396476', 'Master Card', 'March', 2027, 982);";
        statement.executeUpdate(sql);
        //connection.commit();
        System.out.println("Data inserted successfully");
    }


    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseManager databaseManager = new DatabaseManager();
      //  databaseManager.createTable();
        databaseManager.insertData();
    }
}


