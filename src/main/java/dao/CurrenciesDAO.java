package dao;

import Config.DbConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Currencies;
import java.util.List;

public class CurrenciesDAO {

    public List<Currencies> findAll() throws SQLException, ClassNotFoundException {

        Connection connection = DbConnect.getConnection();
        List<Currencies> currencies = new ArrayList<>();
        String sql = "select * from Currencies";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Currencies currency = new Currencies();
            currency.setId(resultSet.getInt("id"));
            currency.setCode(resultSet.getString("code"));
            currency.setFullname(resultSet.getString("fullname"));
            currency.setSign(resultSet.getString("sign"));
            currencies.add(currency);
        }

        return currencies;

    }
}
