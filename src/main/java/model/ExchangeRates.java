package model;

import lombok.*;
import org.mapstruct.Mapper;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRates {
    private int id;
    private int BaseCurrencyid;
    private int targetCurrencyid;
    private BigDecimal rate;
}
