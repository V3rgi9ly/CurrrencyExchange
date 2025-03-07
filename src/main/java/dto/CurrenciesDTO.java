package dto;

import lombok.*;

@NoArgsConstructor
@Data
public final class CurrenciesDTO {
    private int id;
    private String code;
    private String fullname;
    private String sign;
}
