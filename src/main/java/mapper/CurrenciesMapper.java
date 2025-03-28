package mapper;


import dto.CurrenciesDTO;
import dto.CurrenciesExchangeDTO;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import model.CurrenciesExchange;
import model.ExchangeRates;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import model.Currencies;
import java.util.List;

@Mapper
public interface CurrenciesMapper {

    CurrenciesMapper INSTANCE= Mappers.getMapper(CurrenciesMapper.class);


    CurrenciesDTO toCurrenciesDTO(Currencies currency);
    ExchangeRatesDTO toExchangeRatesDTO(ExchangeRates currency);
    List<CurrenciesDTO> currenciesDTOList(List<Currencies> currencies);
    ExchangeRates toExchangeRatesUserAddDTO(UserAddExchangeRateDTO userAddExchangeRateDTO);
    ExchangeRates toExchangeRates(ExchangeRatesDTO userAddExchangeRateDTO);
    CurrenciesExchangeDTO toCurrenciesExchange(CurrenciesExchange currenciesExchange);



    List<ExchangeRatesDTO> exchangeDTOList(List<ExchangeRates> exchangeRates);
}
