package model;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.Data;


public class Currency {
    private  int id;
    private  String code;
    private  String fullname;
    private  String sign;

    public Currency() {

    }
    public Currency(int id, String code, String fullname, String sign) {
        this.id = id;
        this.code = code;
        this.fullname = fullname;
        this.sign = sign;
    }

}
