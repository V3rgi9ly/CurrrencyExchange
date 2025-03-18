package Config;


import dto.CurrenciesDTO;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import model.ExchangeRates;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import model.Currencies;
import java.util.List;

@Mapper
public interface CurrenciesMapper {

    CurrenciesMapper INSTANCE= Mappers.getMapper(CurrenciesMapper.class);

    CurrenciesDTO toDTO(Currencies currency);
    ExchangeRatesDTO toDTO(ExchangeRates currency);
    List<CurrenciesDTO> currenciesDTOList(List<Currencies> currencies);
    ExchangeRates toExchangeRates(UserAddExchangeRateDTO userAddExchangeRateDTO);
    ExchangeRates toExchangeRates(ExchangeRatesDTO userAddExchangeRateDTO);


    List<ExchangeRatesDTO> exchangeDTOList(List<ExchangeRates> exchangeRates);
}
