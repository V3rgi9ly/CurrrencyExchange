package dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrenciesDTO {
    private int id;
    private String code;
    private String fullname;
    private String sign;

}
