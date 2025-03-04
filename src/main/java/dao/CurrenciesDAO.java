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

    public List<model.Currency> searchCurrency() throws SQLException {

        Connection connection = DbConnect.getConnection();
        List<model.Currency> currencies = new ArrayList<model.Currency>();
        String sql = "select * from currencies";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            currencies.add(new model.Currency(resultSet.getInt("id"),
                    resultSet.getString("code"),
                    resultSet.getString("fullname"),
                    resultSet.getString("sign")));
        }

        return currencies;

    }
}
