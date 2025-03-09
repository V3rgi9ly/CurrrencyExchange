package Config;


import dto.CurrenciesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import model.Currencies;
import java.util.List;

@Mapper()
public interface CurrenciesMapper {

    CurrenciesMapper INSTANCE= Mappers.getMapper(CurrenciesMapper.class);

    CurrenciesDTO toDTO(Currencies currency);
    List<CurrenciesDTO> currenciesDTOList(List<Currencies> currencies);
}
