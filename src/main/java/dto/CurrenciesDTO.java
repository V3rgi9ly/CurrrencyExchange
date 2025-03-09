package dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final  class CurrenciesDTO {
    private int id;
    private String code;
    private String fullname;
    private String sign;

}
