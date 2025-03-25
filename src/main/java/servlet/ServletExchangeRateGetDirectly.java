package servlet;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ExchangeRatesDTO;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import service.ExchangeRatesService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/exchange")
public class ServletExchangeRateGetDirectly {

    private final ExchangeRatesService exchangeRatesService=ExchangeRatesService.getInstance();
    private ExchangeRatesDTO exchangeRatesDTO=new ExchangeRatesDTO();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from=req.getParameter("from");
        String to=req.getParameter("to");
        int amount=Integer.parseInt(req.getParameter("amount"));


        PrintWriter out=resp.getWriter();
        exchangeRatesDTO=exchangeRatesService.getCurrencyPairDireclty(from,to,amount);

        String employeeJsonString = this.gson.toJson(exchangeRatesDTO);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();

    }

}
