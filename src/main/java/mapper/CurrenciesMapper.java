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
    List<CurrenciesDTO> currenciesDTOList(List<Currencies> currencies);
    CurrenciesExchangeDTO toCurrenciesExchange(CurrenciesExchange currenciesExchange);

}
