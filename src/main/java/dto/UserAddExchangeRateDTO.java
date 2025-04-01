package dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class UserAddExchangeRateDTO {
    private CurrenciesDTO BaseCurrencyid;
    private CurrenciesDTO targetCurrencyid;
    private BigDecimal rate;

}
