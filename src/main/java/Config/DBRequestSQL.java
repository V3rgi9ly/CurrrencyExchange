package Config;

import lombok.Getter;

public class DBRequestSQL {
    @Getter
    private static final DBRequestSQL instance = new DBRequestSQL();

    private DBRequestSQL() {
    }

    public final String requestGetAllCurrencies = "select * from Currencies";
    public final String requestGetCurrency = "select * from Currencies where code=?";
    public final String requestaAddNewCurrency = "INSERT INTO Currencies (code, fullname, sign) values (?,?,?)";
    public final String requestGetAllExchangeRate = "select * from ExchangeRate";
    public final String requestGetExchangeRate = "select * from ExchangeRate " +
            "JOIN Currencies ON  Currencies.id=ExchangeRate.id " +
            "where Currencies.code IN (?, ?)";
    public final String requestAddExchangeRate = "INSERT INTO ExchangeRate (BaseCurrencyid, TargetCurrencyId, rate) values (?,?,?)";
    public final String requestUpdateRate = "update ExchangeRate set rate=? where BaseCurrencyid=? and TargetCurrencyid=?";

    public final String getDirectExchangeRate = "select*from ExchangeRate where BaseCurrencyid=? and TargetCurrencyid=?";
    public final String getReverseExchangeRate="select*from ExchangeRate "
            +"JOIN Currencies c1 ON c1.id=ExchangeRate.BaseCurrencyid "+
            "JOIN Currencies c2 ON c2.id=ExchangeRate.TargetCurrencyid "+
            "where c1.code=? and c2.code=? ";

    public final String getExchangeRateFromCurrencyPairs="";




}
