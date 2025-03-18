package model;

import lombok.*;

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
