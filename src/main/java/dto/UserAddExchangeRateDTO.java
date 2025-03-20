package dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class UserAddExchangeRateDTO {
    private int BaseCurrencyid;
    private int targetCurrencyid;
    private BigDecimal rate;

}
