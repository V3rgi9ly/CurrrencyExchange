package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserAddCurrencyDTO {
    private String code;
    private String fullname;
    private String sign;
}
