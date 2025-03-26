package service;

import dto.CurrenciesDTO;
import dto.CurrenciesExchangeDTO;
import mapper.CurrenciesMapper;
import dao.ExchangeRatesDAO;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import lombok.Getter;
import model.Currencies;
import model.CurrenciesExchange;
import model.ExchangeRates;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeRatesService {

    @Getter
    private static final ExchangeRatesService instance = new ExchangeRatesService();
    private final CurrenciesMapper currenciesMapper=CurrenciesMapper.INSTANCE;
    private final ExchangeRatesDAO exchangeRatesDAO;
    private final CurrenciesService currenciesService=CurrenciesService.getInstance();

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

    public CurrenciesExchangeDTO getCurrencyPairDireclty(String from, String to, int amount) {
        CurrenciesDTO currenciesDTO=currenciesService.findByCode(from);
        CurrenciesDTO currenciesDTO2=currenciesService.findByCode(to);

        if ((findByCode(from).getId()==(currenciesDTO.getId())) && (findByCode(to).getId()==(currenciesDTO2.getId()))) {
            CurrenciesExchange exchangeRates=exchangeRatesDAO.getCurrencyPairDireclty(from,to);
            return currenciesMapper.toCurrenciesExchange(exchangeRates);
        }
        else if ((findByCode(to).getId()==(currenciesDTO.getId())) && (findByCode(from).getId()==(currenciesDTO2.getId()))) {
            CurrenciesExchange exchangeRates=exchangeRatesDAO.getReverseExchangeRate(from,to);

            return currenciesMapper.toCurrenciesExchange(exchangeRates);
        }
        else {
            ExchangeRates exchangeRates=exchangeRatesDAO.findByCode(from);
            ExchangeRates exchangeRates2=exchangeRatesDAO.findByCode(to);
            CurrenciesExchange exchange=exchangeRatesDAO.getExchangeRateFromCurrencyPairs(exchangeRates,exchangeRates2);
            return currenciesMapper.toCurrenciesExchange(exchange);
        }

    }
}
