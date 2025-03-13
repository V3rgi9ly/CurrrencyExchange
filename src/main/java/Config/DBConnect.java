package Config;

import lombok.Getter;
import model.Currencies;
import model.ExchangeRates;

import javax.swing.*;
import java.sql.*;

public class DBConnect {
    @Getter
    private static final DBConnect intstance = new DBConnect();
    private final DBConfig dbBaseConfig;

    private DBConnect() {
        this.dbBaseConfig = DBConfig.getInstance();
    }


    public ResultSet connection(String requestSQL, String... params) {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(dbBaseConfig.databaseURI, dbBaseConfig.username, dbBaseConfig.password);
            PreparedStatement preparedStatement = connection.prepareStatement(requestSQL);

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            ResultSet resultSet = null;
            if (requestSQL.trim().toUpperCase().startsWith("SELECT")
                    || requestSQL.trim().toUpperCase().startsWith("WITH")) {
                resultSet = preparedStatement.executeQuery();


            } else {
                preparedStatement.executeUpdate();
                connection.close();
            }

            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException("Error in connection");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error-ClassNotFound");
        }


    }

    public ResultSet connection(String requestSQL, Object params) {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(dbBaseConfig.databaseURI, dbBaseConfig.username, dbBaseConfig.password);
            PreparedStatement preparedStatement = connection.prepareStatement(requestSQL);


            if (params instanceof ExchangeRates){
                preparedStatement.setInt(1, ((ExchangeRates) params).getBaseCurrencyid());
                preparedStatement.setInt(2, ((ExchangeRates) params).getTargetCurrencyid());
                preparedStatement.setBigDecimal(3, ((ExchangeRates) params).getRate());
            }
            else if (params instanceof Currencies){
                preparedStatement.setString(1, ((Currencies) params).getCode());
                preparedStatement.setString(2, ((Currencies) params).getFullname());
                preparedStatement.setString(3, ((Currencies) params).getSign());
            }
            else {
                preparedStatement.setString(1, (String) params);
            }


            ResultSet resultSet = null;
            if (requestSQL.trim().toUpperCase().startsWith("SELECT")
                    || requestSQL.trim().toUpperCase().startsWith("WITH")) {
                resultSet = preparedStatement.executeQuery();


            } else {
                preparedStatement.executeUpdate();
                connection.close();
            }

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException("Error in connection");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error-ClassNotFound");
        }


    }


}
