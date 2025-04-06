package Config;

import lombok.Getter;
import java.sql.*;

public class DBConnect {
    @Getter
    private static final DBConnect intstance = new DBConnect();
    private final DBConfig dbBaseConfig;

    private DBConnect() {
        this.dbBaseConfig = DBConfig.getInstance();
    }

    public ResultSet connection(String requestSQL, Object... param) {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(dbBaseConfig.databaseURI, dbBaseConfig.username, dbBaseConfig.password);
            PreparedStatement preparedStatement = connection.prepareStatement(requestSQL);


            for (int i = 0; i < param.length; i++) {
                preparedStatement.setObject(i + 1, param[i]);
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
