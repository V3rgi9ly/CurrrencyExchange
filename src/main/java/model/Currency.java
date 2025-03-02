package model;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Currency {
    private final int id;
    private final String code;
    private final String fullname;
    private final String sign;

}
