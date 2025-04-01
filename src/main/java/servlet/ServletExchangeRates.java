package servlet;

import service.CurrenciesService;
import service.ExchangeRatesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/exchangeRate")
public class ServletExchangeRates extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final ExchangeRatesService exchangeRatesService=ExchangeRatesService.getInstance();
    private final CurrenciesService currenciesService=CurrenciesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ExchangeRatesDTO> exchangeRatesDTOS=exchangeRatesService.findAll();
        String employeeJsonString = this.gson.toJson(exchangeRatesDTOS);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrency= req.getParameter("BaseCurrencyid");
        String targetCurrency= req.getParameter("TargetCurrencyId");
        BigDecimal rate=new BigDecimal(req.getParameter("rate"));

        List<Object> element=new ArrayList<>();
        element.add(baseCurrency);
        element.add(targetCurrency);
        element.add(String.valueOf(rate));
        for (Object s : element) {
            if(s==null ){
                throw new RuntimeException("params equals null or empty");
            }
        }

        PrintWriter out = resp.getWriter();

        if (currenciesService.findByCode(baseCurrency)==null || currenciesService.findByCode(targetCurrency)==null) {
            throw new RuntimeException("currency not found");
        }

        UserAddExchangeRateDTO exchangeRatesDTO = new UserAddExchangeRateDTO(currenciesService.findByCode(baseCurrency), currenciesService.findByCode(targetCurrency), rate);
        exchangeRatesService.save(exchangeRatesDTO);
        String employeeJsonString = this.gson.toJson(exchangeRatesDTO);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(201);
        out.print(employeeJsonString);
        out.flush();
    }
}
