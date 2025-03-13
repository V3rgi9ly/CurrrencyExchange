package dao;

import model.Currencies;

import java.util.List;

public interface CrudCurrencies<T, K> {
    List<T> findAll();
    T findByCode(String code1);
    void save(K entity);

}
