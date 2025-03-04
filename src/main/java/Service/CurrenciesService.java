package Service;

import dao.CurrenciesDAO;
import dto.CurrenciesDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrenciesService {

    public List<CurrenciesDTO> getCurrencies() throws SQLException {
        List<CurrenciesDTO> currenciesDTOS = new ArrayList<CurrenciesDTO>();
        CurrenciesDAO currenciesDAO = new CurrenciesDAO();



    }
}
