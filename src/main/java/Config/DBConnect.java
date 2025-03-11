package Config;

import lombok.Getter;

import javax.swing.*;
import java.sql.*;

public class DBConnect {
    @Getter
    private static final DBConnect intstance=new DBConnect();
    private final DBConfig dbBaseConfig;

    private DBConnect() {
        this.dbBaseConfig=DBConfig.getInstance();
    }

    public ResultSet connection(String requestSQL, Object... params)  {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection= DriverManager.getConnection(dbBaseConfig.databaseURI,dbBaseConfig.username, dbBaseConfig.password);
            PreparedStatement preparedStatement=connection.prepareStatement(requestSQL);

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }

            ResultSet resultSet=preparedStatement.executeQuery();
            return resultSet;
        }
        catch (SQLException e) {
            throw new RuntimeException("Error in connection",e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Error-ClassNotFound",e);
        }


    }



}
