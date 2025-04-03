package Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;

public class OutputJsonFormat <T> {


    private String message;
    private transient  Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public OutputJsonFormat() {

    }
    private OutputJsonFormat(String message) {
        this.message = message;
    }

    public void setMessageError(HttpServletResponse response, String message) {

        OutputJsonFormat error = new OutputJsonFormat(message);

        try {
            PrintWriter out = response.getWriter();
            String exceptionJsonString = this.gson.toJson(error);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(exceptionJsonString);
            out.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setMessageResult(HttpServletResponse response, T message) {

        try {
            PrintWriter out = response.getWriter();
            String exceptionJsonString = this.gson.toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(exceptionJsonString);
            out.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
