package dao;

import model.Currencies;

import java.util.List;

public interface CrudCurrencies<T> {
    List<T> findAll();
    T findByCode(String currencyCode);
    void save(String code, String fullname, String sign);

}
