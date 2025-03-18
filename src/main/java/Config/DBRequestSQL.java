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
    public final String requestGetAllExchangeRate="select * from ExchangeRate";
    public final String requestGetExchangeRate="select * from ExchangeRate " +
            "JOIN Currencies ON  Currencies.id=ExchangeRate.id " +
            "where Currencies.code IN (?, ?)";
    public final String requestAddExchangeRate="INSERT INTO ExchangeRate (BaseCurrencyid, TargetCurrencyId, rate) values (?,?,?)";
    public final String requestUpdateRate="update ? set rate=?";


}
