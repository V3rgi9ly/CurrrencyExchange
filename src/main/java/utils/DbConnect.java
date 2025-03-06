package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect  {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Sergey\\.m2\\repository\\javax\\servlet\\javax.servlet-api\\CurrrencyExchange\\Currencies.db", "", "");
        return connection;
    }
}
