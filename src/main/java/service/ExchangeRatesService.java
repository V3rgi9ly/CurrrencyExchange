package service;

import mapper.CurrenciesMapper;
import dao.ExchangeRatesDAO;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import lombok.Getter;
import model.ExchangeRates;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeRatesService {

    @Getter
    private static final ExchangeRatesService instance = new ExchangeRatesService();
    private final CurrenciesMapper currenciesMapper=CurrenciesMapper.INSTANCE;
    private final ExchangeRatesDAO exchangeRatesDAO;

    private ExchangeRatesService() {
        this.exchangeRatesDAO= ExchangeRatesDAO.getInstance();
    }

    public List<ExchangeRatesDTO> findAll() {
        List<ExchangeRates> exchangeRates= exchangeRatesDAO.findAll();
        return currenciesMapper.exchangeDTOList(exchangeRates);
    }

    public ExchangeRatesDTO findByCode(String code1) {
        ExchangeRates exchangeRates=exchangeRatesDAO.findByCode(code1);
        return  currenciesMapper.toExchangeRatesDTO(exchangeRates);
    }

    public void save(UserAddExchangeRateDTO userAddExchangeRateDTO) {
        ExchangeRates exchangeRates=currenciesMapper.toExchangeRatesUserAddDTO(userAddExchangeRateDTO);
        exchangeRatesDAO.save(exchangeRates);
    }

    public void update(ExchangeRatesDTO exchangeRatesDTO, BigDecimal rate) {
        ExchangeRates exchangeRates=currenciesMapper.toExchangeRates(exchangeRatesDTO);
        exchangeRatesDAO.update(exchangeRates,rate);
    }
}
