package dao;

import utils.DbConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Currency;
import java.util.List;

public class CurrenciesDAO {

    public List<Currency> searchCurrency() throws SQLException, ClassNotFoundException {

        Connection connection = DbConnect.getConnection();
        List<Currency> currencies = new ArrayList<>();
        String sql = "select * from Currencies";
        Currency currency = new Currency();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            currency.setId(resultSet.getInt("id"));
            currency.setCode(resultSet.getString("code"));
            currency.setFullname(resultSet.getString("fullname"));
            currency.setSign(resultSet.getString("sign"));
            currencies.add(currency);
            resultSet.close();
        }

        return currencies;

    }
}
