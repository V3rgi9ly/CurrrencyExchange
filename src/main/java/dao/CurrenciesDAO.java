package dao;

import utils.DbConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CurrenciesDAO {

    public List<Currency> searchCurrency() throws SQLException {
        Currency currency = null;
        Connection connection= DbConnect.getConnection();
        List<Currency> currencies= new ArrayList<Currency>();
        String sql="select * from currencies";

        Statement statement=connection.createStatement();

        ResultSet resultSet=statement.executeQuery(sql);


    }
}
