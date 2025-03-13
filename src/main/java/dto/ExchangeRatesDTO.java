package dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public final class ExchangeRatesDTO {
    private int id;
    private int BaseCurrencyid;
    private int targetCurrencyid;
    private BigDecimal rate;
}

