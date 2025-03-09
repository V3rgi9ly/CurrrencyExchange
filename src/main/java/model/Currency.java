package model;




import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private int id;
    private String code;
    private String fullname;
    private String sign;


}
