package dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public final class CurrenciesDTO {
    private int id;
    private String code;
    private String fullname;
    private String sign;
}
