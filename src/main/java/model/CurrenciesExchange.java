package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrenciesExchange {

    private int BaseCurrencyid;
    private int TargetCurrencyid;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
}
