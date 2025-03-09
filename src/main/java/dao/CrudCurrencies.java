package dao;

import model.Currencies;

import java.util.List;

public interface CrudCurrencies {
    void Create(Currencies currency);
    void Update(Currencies currency);
    void Read(Currencies currency);
    List<Currencies> GetAllCurrencies();


}
