package dao;

import model.Currency;

import java.util.List;

public interface CrudCurrencies {
    void Create(Currency currency);
    void Update(Currency currency);
    void Read(Currency currency);
    List<Currency> GetAllCurrencies();


}
