package dto;


import lombok.*;

import java.math.BigDecimal;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrenciesExchangeDTO {

    private CurrenciesDTO BaseCurrencyid;
    private CurrenciesDTO targetCurrencyid;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;

}
