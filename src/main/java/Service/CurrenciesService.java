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
@NoArgsConstructor
public class CurrenciesService {

    private  CurrenciesDAO currenciesDAO=new CurrenciesDAO();
    private final CurrenciesMapper mapper = CurrenciesMapper.INSTANCE;


    public List<CurrenciesDTO> getCurrencies() throws SQLException, ClassNotFoundException {

        List<Currency> currency = currenciesDAO.searchCurrency();


        return mapper.currenciesDTOList(currency);
    }
}
