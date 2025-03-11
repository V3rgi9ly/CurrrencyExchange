package dao;

import Config.DBConnect;
import Config.DBRequestSQL;


import java.sql.*;
import java.util.ArrayList;

import lombok.Getter;
import model.Currencies;

import javax.swing.*;
import java.util.List;
import java.util.PrimitiveIterator;

public class CurrenciesDAO implements CrudCurrencies{

    @Getter
    private static final CurrenciesDAO instance=new CurrenciesDAO();

    private final DBConnect dbConnect;
    private final DBRequestSQL dbRequestSQL;

    private CurrenciesDAO() {
        this.dbConnect = DBConnect.getIntstance();
        this.dbRequestSQL = DBRequestSQL.getInstance();
    }

    @Override
    public List<Currencies> findAll() {
        try {
            ResultSet resultSet = dbConnect.connection(dbRequestSQL.requestGetAllCurrencies);
            List<Currencies> currencies = new ArrayList<>();

            while (resultSet.next()) {
                Currencies currency = new Currencies();
                currency.setId(resultSet.getInt("id"));
                currency.setCode(resultSet.getString("code"));
                currency.setFullname(resultSet.getString("fullname"));
                currency.setSign(resultSet.getString("sign"));
                currencies.add(currency);
            }

            return currencies;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean findCode(String code) {
        if ()
    }

    @Override
    public Currencies findByCode(String currencyCode)  {
        try {
            ResultSet resultSet = dbConnect.connection(dbRequestSQL.requestGetCurrency, currencyCode.toUpperCase());
            Currencies currencies = new Currencies();

            while (resultSet.next()) {
                currencies.setId(resultSet.getInt("id"));
                currencies.setCode(resultSet.getString("code"));
                currencies.setFullname(resultSet.getString("fullname"));
                currencies.setSign(resultSet.getString("sign"));
            }

            return currencies;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(String code, String fullname, String sign)  {
        dbConnect.connection(dbRequestSQL.requestaAddNewCurrency, code, fullname, sign);
    }
}
