package Service;

import Config.CurrenciesMapper;
import dao.CurrenciesDAO;
import dao.CurrencyDAO;
import dto.CurrenciesDTO;
import model.Currencies;

import java.sql.SQLException;
import java.util.Currency;
import java.util.List;

public class CurrencyService {

    private static CurrencyService instance;
    private final CurrencyDAO currenciesDAO = new CurrencyDAO();
    private final CurrenciesMapper mapper = CurrenciesMapper.INSTANCE;

    public static CurrencyService getInstance() {
        if (instance == null) {
            instance = new CurrencyService();
        }
        return instance;
    }

    public CurrenciesDTO find(String code) {
        try {
            Currencies currency = currenciesDAO.find(code);
            return mapper.toDTO(currency);
        } catch (SQLException e){
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
