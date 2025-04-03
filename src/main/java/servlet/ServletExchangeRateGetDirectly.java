package servlet;


import Config.OutputJsonFormat;
import dto.CurrenciesExchangeDTO;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import service.CurrenciesService;
import service.ExchangeRatesService;

import java.math.BigDecimal;

@WebServlet("/exchange")
public class ServletExchangeRateGetDirectly extends HttpServlet {

    private final ExchangeRatesService exchangeRatesService=ExchangeRatesService.getInstance();
    private CurrenciesExchangeDTO currenciesExchangeDTO =new CurrenciesExchangeDTO();
    private final CurrenciesService currenciesService=CurrenciesService.getInstance();
    private final OutputJsonFormat outputJsonFormat = new OutputJsonFormat();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {

        try {
            String from=req.getParameter("from");
            String to=req.getParameter("to");
            BigDecimal amount=BigDecimal.valueOf(Double.parseDouble(req.getParameter("amount")));

            if (currenciesService.findByCode(from) == null || currenciesService.findByCode(to) == null) {
                outputJsonFormat.setMessageError(resp, "the currency from the currency pair does not exist");
                resp.setStatus(404);
            }

            currenciesExchangeDTO =exchangeRatesService.getCurrencyPairDireclty(from,to,amount);
            outputJsonFormat.setMessageResult(resp,currenciesExchangeDTO);
            resp.setStatus(200);
        } catch (RuntimeException e) {
            outputJsonFormat.setMessageError(resp, "Error: " + e.getMessage());
            resp.setStatus(500);
        }

    }

}
