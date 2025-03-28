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

    public CurrenciesExchangeDTO getCurrencyPairDireclty(String from, String to, BigDecimal amount) {

        if (exchangeRatesDAO.findAll().contains(exchangeRatesDAO.findByCode(from+to))){
            ExchangeRates exchangeRates=exchangeRatesDAO.findByCode(from+to);
            CurrenciesExchange currenciesExchange=new CurrenciesExchange();
            currenciesExchange.setBaseCurrencyid(exchangeRates.getBaseCurrencyid());
            currenciesExchange.setTargetCurrencyid(exchangeRates.getTargetCurrencyid());
            currenciesExchange.setRate(exchangeRates.getRate());
            currenciesExchange.setAmount(amount);
            currenciesExchange.setConvertedAmount(amount.multiply(exchangeRates.getRate()));
            return currenciesMapper.toCurrenciesExchange(currenciesExchange);
        }
        else if (findAll().contains(findByCode(to+from))) {
            ExchangeRates exchangeRatesReverse=exchangeRatesDAO.findByCode(to+from);
            CurrenciesExchange currenciesExchangeReverse=new CurrenciesExchange();
            currenciesExchangeReverse.setBaseCurrencyid(exchangeRatesReverse.getTargetCurrencyid());
            currenciesExchangeReverse.setTargetCurrencyid(exchangeRatesReverse.getBaseCurrencyid() );
            currenciesExchangeReverse.setRate(BigDecimal.valueOf(1.0).divide(exchangeRatesReverse.getRate()));
            currenciesExchangeReverse.setAmount(amount);
            currenciesExchangeReverse.setConvertedAmount(amount.multiply(exchangeRatesReverse.getRate()));
            return currenciesMapper.toCurrenciesExchange(currenciesExchangeReverse);
        }
        else {
            CurrenciesExchange getExchangeRateFromCurrencyPairs=new CurrenciesExchange();
            if (exchangeRatesDAO.findAll().contains(exchangeRatesDAO.findByCode("USD"+from)) && exchangeRatesDAO.findAll().contains(exchangeRatesDAO.findByCode("USD"+to))){
                getExchangeRateFromCurrencyPairs.setBaseCurrencyid((exchangeRatesDAO.findByCode("USD"+from).getTargetCurrencyid()));
                getExchangeRateFromCurrencyPairs.setTargetCurrencyid((exchangeRatesDAO.findByCode("USD"+to).getTargetCurrencyid()));
                getExchangeRateFromCurrencyPairs.setRate((BigDecimal.valueOf(1.0).divide(exchangeRatesDAO.findByCode("USD"+from).getRate())).multiply(exchangeRatesDAO.findByCode("USD"+to).getRate()));
                getExchangeRateFromCurrencyPairs.setAmount(amount);
                getExchangeRateFromCurrencyPairs.setConvertedAmount(amount.multiply(getExchangeRateFromCurrencyPairs.getRate()));
                return currenciesMapper.toCurrenciesExchange(getExchangeRateFromCurrencyPairs);
            }
            else {
                throw new RuntimeException("Валюта не найдена!");
            }

        }
    }
}
