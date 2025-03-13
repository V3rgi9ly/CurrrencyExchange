package servlet;


import Service.ExchangeRatesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ExchangeRatesDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/exchangeRate/*")
public class ServletExchangeRate extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final ExchangeRatesService exchangeRatesService=ExchangeRatesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getPathInfo().split("/");
        String code=requestURI[1];
        if (code.length()<=5  ||  code.length()>6) {
            throw new ServletException("Invalid request path");
        }
        PrintWriter out = resp.getWriter();
        ExchangeRatesDTO exchangeRatesDTO=ExchangeRatesService.getInstance().findByCode(code);
        if (exchangeRatesDTO==null) {
            throw new ServletException("Invalid request path");
        }

        String employeeJsonString = this.gson.toJson(exchangeRatesDTO);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();


    }
}
