package dao;

import Config.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import model.Currencies;
import java.util.List;


public class CurrencyDAO {
    public Currencies find(String currencyCode) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnect.getConnection();
        Currencies currencies=new Currencies();
        String codeC=currencyCode.toUpperCase();
        String sql="select * from currencies where code=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, codeC);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            currencies.setId(resultSet.getInt("id"));
            currencies.setCode(resultSet.getString("code"));
            currencies.setFullname(resultSet.getString("fullname"));
            currencies.setSign(resultSet.getString("sign"));
        }

        return currencies;
    }
}
