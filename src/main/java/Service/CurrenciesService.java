package Service;

import Config.CurrenciesMapper;
import dao.CurrenciesDAO;
import dto.CurrenciesDTO;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;


import java.sql.SQLException;
import java.util.ArrayList;
import model.Currency;
import java.util.List;

@RequiredArgsConstructor
public class CurrenciesService {

    private static CurrenciesService instance;
    private final CurrenciesDAO currenciesDAO=new CurrenciesDAO();
    private final CurrenciesMapper mapper = CurrenciesMapper.INSTANCE;

    public static CurrenciesService getInstance() {
        if (instance == null) {
            instance=new CurrenciesService();
        }
        return instance;
    }

    public List<CurrenciesDTO> getCurrencies() throws SQLException, ClassNotFoundException {

        List<Currency> currency = currenciesDAO.searchCurrency();

        return mapper.currenciesDTOList(currency);
    }
}
