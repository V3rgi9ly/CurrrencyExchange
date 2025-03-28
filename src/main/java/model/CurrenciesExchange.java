package model;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrenciesExchange {

    private int BaseCurrencyid;
    private int targetCurrencyid;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
}
