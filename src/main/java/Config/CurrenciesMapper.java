package Config;


import dto.CurrenciesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import model.Currency;
import java.util.List;

@Mapper()
public interface CurrenciesMapper {

    CurrenciesMapper INSTANCE= Mappers.getMapper(CurrenciesMapper.class);
    CurrenciesDTO toDTO(Currency currency);
    List<CurrenciesDTO> currenciesDTOList(List<Currency> currencies);
}
