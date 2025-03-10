package Service;

import Config.CurrenciesMapper;
import dao.CurrenciesDAO;
import dto.CurrenciesDTO;


import java.sql.SQLException;

import model.Currencies;

import java.util.List;


public class CurrenciesService {

    private static CurrenciesService instance;
    private final CurrenciesDAO currenciesDAO = new CurrenciesDAO();
    private final CurrenciesMapper mapper = CurrenciesMapper.INSTANCE;

    public static CurrenciesService getInstance() {
        if (instance == null) {
            instance = new CurrenciesService();
        }
        return instance;
    }

    public List<CurrenciesDTO> findAll() {
        try {
            List<Currencies> currency = currenciesDAO.findAll();
            return mapper.currenciesDTOList(currency);
        } catch (SQLException e){
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
