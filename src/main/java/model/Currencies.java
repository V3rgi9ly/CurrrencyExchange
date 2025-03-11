package model;




import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currencies {
    private int id;
    private String code;
    private String fullname;
    private String sign;


}

