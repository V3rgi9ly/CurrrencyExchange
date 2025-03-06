package model;




import lombok.Data;

@Data
public class Currency {
    private int id;
    private String code;
    private String fullname;
    private String sign;

    public Integer getid(){
        return id;
    }
    public void setid(Integer id){
        this.id = id;
    }

    public String getcode(){
        return code;
    }
    public void setcode(String code){
        this.code = code;
    }
    public String getfullname(){
        return fullname;
    }
    public void setfullname(String fullname){
        this.fullname = fullname;
    }
    public String getsign(){
        return sign;
    }
    public void setsign(String sign){
        this.sign = sign;
    }
}
