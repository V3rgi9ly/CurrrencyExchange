package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import Service.CurrenciesService;
import Service.CurrencyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CurrenciesDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static java.lang.System.out;


@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/currencies/*")
public class ServletCurrency extends HttpServlet {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CurrencyService currencyService = CurrencyService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] requestURI = request.getPathInfo().split("/");
        String code=requestURI[1];
        PrintWriter out = response.getWriter();
        CurrenciesDTO currenciesDTO = currencyService.find(code);
        String employeeJsonString = this.gson.toJson(currenciesDTO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();

    }
}
