package dto;

import lombok.*;
import model.Currencies;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExchangeRatesDTO {
    private int id;
    private CurrenciesDTO BaseCurrencyid;
    private CurrenciesDTO targetCurrencyid;
    private BigDecimal rate;


}

