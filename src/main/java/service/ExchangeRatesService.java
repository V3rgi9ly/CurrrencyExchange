package service;

import dao.CurrenciesDAO;
import dto.CurrenciesExchangeDTO;
import dao.ExchangeRatesDAO;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import model.Currencies;
import model.CurrenciesExchange;
import model.ExchangeRates;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesService {


    private final ExchangeRatesDAO exchangeRatesDAO;
    private final CurrenciesDAO currenciesDAO = CurrenciesDAO.getInstance();
    private final CurrenciesService currenciesService = new CurrenciesService();

    public ExchangeRatesService() {
        this.exchangeRatesDAO = ExchangeRatesDAO.getInstance();
    }

    public List<ExchangeRatesDTO> findAll() {
        List<ExchangeRates> exchangeRates = exchangeRatesDAO.findAll();
        List<ExchangeRatesDTO> exchangeRatesDTOs = new ArrayList<ExchangeRatesDTO>();

        for (ExchangeRates rates : exchangeRates) {
            for (Currencies currenciesBase : currenciesDAO.findAll()) {
                for (Currencies currenciesTarget : currenciesDAO.findAll()) {
                    if (rates.getBaseCurrencyid() == currenciesBase.getId() && rates.getTargetCurrencyid() == currenciesTarget.getId()) {
                        exchangeRatesDTOs.add(findByCode(currenciesBase.getCode()+currenciesTarget.getCode()));
                    }
                }
            }
        }
        return exchangeRatesDTOs;

    }

    public ExchangeRatesDTO findByCode(String code1) {
        ExchangeRates exchangeRates = exchangeRatesDAO.findByCode(code1);
        ExchangeRatesDTO exchangeRatesDTO = new ExchangeRatesDTO();
        exchangeRatesDTO.setId(exchangeRates.getId());
        exchangeRatesDTO.setBaseCurrencyid((currenciesService.findByCode(code1.substring(0, 3))));
        exchangeRatesDTO.setTargetCurrencyid(currenciesService.findByCode(code1.substring(3)));
        exchangeRatesDTO.setRate(exchangeRates.getRate().setScale(2, RoundingMode.HALF_EVEN));

        return exchangeRatesDTO;
    }

    public void save(UserAddExchangeRateDTO userAddExchangeRateDTO) {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setBaseCurrencyid(userAddExchangeRateDTO.getBaseCurrencyid().getId());
        exchangeRates.setTargetCurrencyid(userAddExchangeRateDTO.getTargetCurrencyid().getId());
        exchangeRates.setRate(userAddExchangeRateDTO.getRate().setScale(2, RoundingMode.HALF_UP));
        exchangeRatesDAO.save(exchangeRates);
    }

    public void update(ExchangeRatesDTO exchangeRatesDTO, BigDecimal rate) {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setId(exchangeRatesDTO.getId());
        exchangeRates.setBaseCurrencyid(exchangeRatesDTO.getBaseCurrencyid().getId());
        exchangeRates.setTargetCurrencyid(exchangeRatesDTO.getTargetCurrencyid().getId());
        exchangeRates.setRate(rate);
        exchangeRatesDAO.update(exchangeRates, rate.setScale(2, RoundingMode.HALF_UP));
    }

    public CurrenciesExchangeDTO getCurrencyPairDireclty(String from, String to, BigDecimal amount) {
        CurrenciesExchangeDTO currenciesExchangeDTO = new CurrenciesExchangeDTO();
        if (exchangeRatesDAO.findAll().contains(exchangeRatesDAO.findByCode(from + to))) {
            ExchangeRates exchangeRates = exchangeRatesDAO.findByCode(from + to);
            CurrenciesExchange currenciesExchange = new CurrenciesExchange();
            currenciesExchange.setBaseCurrencyid(exchangeRates.getBaseCurrencyid());
            currenciesExchange.setTargetCurrencyid(exchangeRates.getTargetCurrencyid());
            currenciesExchange.setRate(exchangeRates.getRate().setScale(2,RoundingMode. HALF_UP));
            currenciesExchange.setAmount(amount);
            currenciesExchange.setConvertedAmount(amount.multiply(exchangeRates.getRate()));


            currenciesExchangeDTO.setBaseCurrencyid(currenciesService.findByCode(from));
            currenciesExchangeDTO.setTargetCurrencyid(currenciesService.findByCode(to));
            currenciesExchangeDTO.setRate(exchangeRates.getRate().setScale(2, RoundingMode.HALF_UP));
            currenciesExchangeDTO.setAmount(currenciesExchange.getAmount());
            currenciesExchangeDTO.setConvertedAmount(currenciesExchange.getConvertedAmount().setScale(2, RoundingMode.HALF_UP));
            return currenciesExchangeDTO;
        } else if (exchangeRatesDAO.findAll().contains(exchangeRatesDAO.findByCode(to + from))) {
            ExchangeRates exchangeRatesReverse = exchangeRatesDAO.findByCode(to + from);
            CurrenciesExchange currenciesExchangeReverse = new CurrenciesExchange();
            currenciesExchangeReverse.setBaseCurrencyid(exchangeRatesReverse.getTargetCurrencyid());
            currenciesExchangeReverse.setTargetCurrencyid(exchangeRatesReverse.getBaseCurrencyid());
            currenciesExchangeReverse.setRate(BigDecimal.valueOf(1.0).divide(exchangeRatesReverse.getRate(), 2, RoundingMode. HALF_UP));
            currenciesExchangeReverse.setAmount(amount);
            currenciesExchangeReverse.setConvertedAmount(amount.multiply(exchangeRatesReverse.getRate()));

            currenciesExchangeDTO.setBaseCurrencyid(currenciesService.findByCode(to));
            currenciesExchangeDTO.setTargetCurrencyid(currenciesService.findByCode(from));
            currenciesExchangeDTO.setRate(currenciesExchangeReverse.getRate().setScale(2, RoundingMode. HALF_UP));
            currenciesExchangeDTO.setAmount(currenciesExchangeReverse.getAmount());
            currenciesExchangeDTO.setConvertedAmount(currenciesExchangeReverse.getConvertedAmount().setScale(2, RoundingMode. HALF_UP));
            return currenciesExchangeDTO;

        } else {
            CurrenciesExchange getExchangeRateFromCurrencyPairs = new CurrenciesExchange();
            String currency1 = "USD" + from;
            String currency2 = "USD" + to;
            if ((exchangeRatesDAO.findAll().contains(exchangeRatesDAO.findByCode(currency1))) && (exchangeRatesDAO.findAll().contains(exchangeRatesDAO.findByCode(currency2)))) {
                getExchangeRateFromCurrencyPairs.setBaseCurrencyid((exchangeRatesDAO.findByCode("USD" + from).getTargetCurrencyid()));
                getExchangeRateFromCurrencyPairs.setTargetCurrencyid((exchangeRatesDAO.findByCode("USD" + to).getTargetCurrencyid()));
                getExchangeRateFromCurrencyPairs.setRate((BigDecimal.valueOf(1.0).divide(exchangeRatesDAO.findByCode("USD" + from).getRate(), 2, RoundingMode. HALF_UP)).multiply(exchangeRatesDAO.findByCode("USD" + to).getRate()));
                getExchangeRateFromCurrencyPairs.setAmount(amount);
                getExchangeRateFromCurrencyPairs.setConvertedAmount(amount.multiply(getExchangeRateFromCurrencyPairs.getRate()));

                currenciesExchangeDTO.setBaseCurrencyid(currenciesService.findByCode(from));
                currenciesExchangeDTO.setTargetCurrencyid(currenciesService.findByCode(to));
                currenciesExchangeDTO.setRate(getExchangeRateFromCurrencyPairs.getRate().setScale(2, RoundingMode. HALF_UP));
                currenciesExchangeDTO.setAmount(getExchangeRateFromCurrencyPairs.getAmount());
                currenciesExchangeDTO.setConvertedAmount(getExchangeRateFromCurrencyPairs.getConvertedAmount().setScale(2, RoundingMode. HALF_UP));

                return currenciesExchangeDTO;
            } else {
                throw new RuntimeException("Валюта не найдена!");
            }

        }
    }

    public boolean findCode(String code) {
        if (exchangeRatesDAO.findByCode(code).getId() != 0) {
            return true;
        }
        return false;
    }
}
