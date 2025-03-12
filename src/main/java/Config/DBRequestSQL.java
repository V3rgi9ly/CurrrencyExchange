package Config;

import lombok.Getter;

public class DBRequestSQL {
    @Getter
    private static final DBRequestSQL instance = new DBRequestSQL();

    private DBRequestSQL() {
    }

    public final String requestGetAllCurrencies="select * from Currencies";
    public final String requestGetCurrency="select * from Currencies where code=?";
    public final String requestaAddNewCurrency="INSERT INTO Currencies (code, fullname, sign) values (?,?,?)";

}
