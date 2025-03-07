package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Service.CurrenciesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CurrenciesDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/currencies")
public class ServletCurrencies extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CurrenciesService currenciesService=CurrenciesService.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        try {
            List<CurrenciesDTO> currenciesDTO = currenciesService.getCurrencies();
            String employeeJsonString = this.gson.toJson(currenciesDTO);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(employeeJsonString);
            out.flush();

        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
