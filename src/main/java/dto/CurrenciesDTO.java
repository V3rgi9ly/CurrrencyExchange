package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public  class CurrenciesDTO {
    private int id;
    private String code;
    private String fullname;
    private String sign;

}
