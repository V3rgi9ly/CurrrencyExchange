package dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExchangeRatesDTO {
    private int id;
    private int BaseCurrencyid;
    private int targetCurrencyid;
    private BigDecimal rate;


}

