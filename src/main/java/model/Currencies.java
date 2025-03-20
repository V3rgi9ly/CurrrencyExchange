package model;




import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currencies {
    private int id;
    private String code;
    private String fullname;
    private String sign;


}

