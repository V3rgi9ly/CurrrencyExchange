package Config;
import lombok.Getter;

public class DBConfig  {
    @Getter
    private static final DBConfig instance=new DBConfig();

    public final String databaseURI="jdbc:sqlite:C:\\Users\\Sergey\\.m2\\repository\\javax\\servlet\\javax.servlet-api\\CurrrencyExchange\\Currencies.db";
    public final String username="";
    public final String password="";
    private DBConfig(){
    }
}
