package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserAddExchangeRateDTO {
    private int BaseCurrencyid;
    private int targetCurrencyid;
    private BigDecimal rate;

}
