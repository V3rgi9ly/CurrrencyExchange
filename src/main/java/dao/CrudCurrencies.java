package dao;


import java.util.List;

public interface CrudCurrencies<T> {
    List<T> findAll();
    T findByCode(String code1);
    void save(T entity);


}
