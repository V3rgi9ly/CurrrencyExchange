package Service;

import Config.CurrenciesMapper;
import dao.CurrenciesDAO;
import dto.CurrenciesDTO;


import java.sql.SQLException;

import dto.UserAddCurrencyDTO;
import lombok.Getter;
import model.Currencies;

import java.util.List;


public class CurrenciesService {

    @Getter
    private static CurrenciesService instance=new CurrenciesService();

    private final CurrenciesDAO currenciesDAO;
    private final CurrenciesMapper mapper = CurrenciesMapper.INSTANCE;

    private CurrenciesService() {
        currenciesDAO=CurrenciesDAO.getInstance();
    }

    public List<CurrenciesDTO> findAll() {
        List<Currencies> currency = currenciesDAO.findAll();
        return mapper.currenciesDTOList(currency);
    }

    public CurrenciesDTO findByCode(String code) {
        Currencies currency = currenciesDAO.findByCode(code);
        return mapper.toDTO(currency);
    }

    public void save(UserAddCurrencyDTO userAddCurrencyDTO) {
        currenciesDAO.save(userAddCurrencyDTO);
    }

    public boolean findCode(String code) {

        if (currenciesDAO.findByCode(code) != null) {
            return true;
        }
       return false;
    }
}
