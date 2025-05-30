package service;

import mapper.CurrenciesMapper;
import dao.CurrenciesDAO;
import dto.CurrenciesDTO;
import model.Currencies;
import java.util.List;

public class CurrenciesService {

    private final CurrenciesDAO currenciesDAO;
    private final CurrenciesMapper mapper = CurrenciesMapper.INSTANCE;

    public CurrenciesService() {
        currenciesDAO=CurrenciesDAO.getInstance();
    }

    public List<CurrenciesDTO> findAll() {
        List<Currencies> currency = currenciesDAO.findAll();
        return mapper.currenciesDTOList(currency);
    }

    public CurrenciesDTO findByCode(String code) {
        Currencies currency = currenciesDAO.findByCode(code);
        return mapper.toCurrenciesDTO(currency);
    }

    public void save(String code, String fullName, String sign) {
        CurrenciesDTO currenciesDTO = new CurrenciesDTO();
        currenciesDTO.setCode(code);
        currenciesDTO.setFullname(fullName);
        currenciesDTO.setSign(sign);
        Currencies currency = mapper.toUserAddCurrencyDTO(currenciesDTO);
        currenciesDAO.save(currency);
    }

    public boolean findCode(String code) {
        if (currenciesDAO.findByCode(code).getId() != 0) {
            return true;
        }
       return false;
    }
}
