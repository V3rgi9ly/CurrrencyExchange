package dto;


import lombok.*;

import java.math.BigDecimal;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrenciesExchangeDTO {

    private int BaseCurrencyid;
    private int targetCurrencyid;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;

}
