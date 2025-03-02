package model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeRates {
    private final int id;
    private final int BaseCurrencyid;
    private final int targetCurrencyid;
    private final BigDecimal rate;
}
