package servlet;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CurrenciesExchangeDTO;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import service.ExchangeRatesService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/exchange")
public class ServletExchangeRateGetDirectly {

    private final ExchangeRatesService exchangeRatesService=ExchangeRatesService.getInstance();
    private CurrenciesExchangeDTO currenciesExchangeDTO =new CurrenciesExchangeDTO();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from=req.getParameter("from");
        String to=req.getParameter("to");
        int amount=Integer.parseInt(req.getParameter("amount"));


        PrintWriter out=resp.getWriter();
        currenciesExchangeDTO =exchangeRatesService.getCurrencyPairDireclty(from,to,amount);

        String employeeJsonString = this.gson.toJson(currenciesExchangeDTO);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();

    }

}
