package Service;

import dao.CurrenciesDAO;
import dto.CurrenciesDTO;

import org.modelmapper.ModelMapper;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrenciesService {

    private final ModelMapper modelMapper;

    public CurrenciesService() {
        this.modelMapper = new ModelMapper();
    }

    public List<CurrenciesDTO> getCurrencies() throws SQLException {
        List<CurrenciesDTO> currenciesDTO = new ArrayList<CurrenciesDTO>();
        CurrenciesDAO currenciesDAO = new CurrenciesDAO();

        for (int i=0; i<currenciesDAO.searchCurrency().size(); i++) {
            modelMapper.map(currenciesDAO.searchCurrency().get(i), CurrenciesDTO.class);
        }
        return currenciesDTO;
    }
}
