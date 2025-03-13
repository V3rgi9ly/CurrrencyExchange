package dao;

import Config.DBConnect;
import Config.DBRequestSQL;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import lombok.Getter;
import model.ExchangeRates;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesDAO implements CrudCurrencies<ExchangeRates, UserAddExchangeRateDTO> {

    @Getter
    private static final ExchangeRatesDAO instance=new ExchangeRatesDAO();

    private final DBConnect dbConnect;
    private final DBRequestSQL dbRequestSQL;

    private ExchangeRatesDAO() {
        this.dbConnect = DBConnect.getIntstance();
        this.dbRequestSQL = DBRequestSQL.getInstance();
    }

    @Override
    public List<ExchangeRates> findAll() {
        try {
            ResultSet resultSet = dbConnect.connection(dbRequestSQL.requestGetAllExchangeRate);
            List<ExchangeRates> exchangeRates = new ArrayList<ExchangeRates>();

            while (resultSet.next()) {
                ExchangeRates exchangeRate = new ExchangeRates();
                exchangeRate.setId(resultSet.getInt("id"));
                exchangeRate.setBaseCurrencyid(resultSet.getInt("BaseCurrencyid"));
                exchangeRate.setTargetCurrencyid(resultSet.getInt("TargetCurrencyid"));
                exchangeRate.setRate(resultSet.getBigDecimal("rate"));
                exchangeRates.add(exchangeRate);
            }

            return exchangeRates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExchangeRates findByCode(String code) {
        try {
            String part1=code.substring(0,3);
            String part2=code.substring(3);
            ResultSet resultSet=dbConnect.connection(dbRequestSQL.requestGetExchangeRate, part1.toUpperCase(), part2.toUpperCase() );
            ExchangeRates exchangeRate=new ExchangeRates();

            while (resultSet.next()) {
                exchangeRate.setId(resultSet.getInt("id"));
                exchangeRate.setBaseCurrencyid(resultSet.getInt("BaseCurrencyid"));
                exchangeRate.setTargetCurrencyid(resultSet.getInt("TargetCurrencyid"));
                exchangeRate.setRate(resultSet.getBigDecimal("rate"));
            }
            return exchangeRate;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(UserAddExchangeRateDTO exchangeRate) {
        dbConnect.connection(dbRequestSQL.requestAddExchangeRate, exchangeRate );
    }
}