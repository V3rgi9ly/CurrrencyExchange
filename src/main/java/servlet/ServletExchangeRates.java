package servlet;

import Config.OutputJsonFormat;
import service.CurrenciesService;
import service.ExchangeRatesService;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/exchangeRate")
public class ServletExchangeRates extends HttpServlet {

    private final ExchangeRatesService exchangeRatesService = new ExchangeRatesService();
    private final CurrenciesService currenciesService = new CurrenciesService();
    private final OutputJsonFormat outputJsonFormat = new OutputJsonFormat();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        List<ExchangeRatesDTO> exchangeRatesDTOS = exchangeRatesService.findAll();
        try {
            outputJsonFormat.setMessageResult(resp, exchangeRatesDTOS);
            resp.setStatus(200);
        } catch (RuntimeException e) {
            outputJsonFormat.setMessageError(resp, "Error: " + e.getMessage());
            resp.setStatus(500);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String baseCurrency = req.getParameter("BaseCurrencyid");
        String targetCurrency = req.getParameter("TargetCurrencyId");
        BigDecimal rate = new BigDecimal(req.getParameter("rate"));

        try {
            List<Object> element = new ArrayList<>();
            element.add(baseCurrency);
            element.add(targetCurrency);
            element.add(String.valueOf(rate));
            for (Object s : element) {
                if (s == null) {
                    resp.setStatus(400);
                    outputJsonFormat.setMessageError(resp, "params equals null or empty");
                }
            }

            if (currenciesService.findByCode(baseCurrency) == null || currenciesService.findByCode(targetCurrency) == null) {
                resp.setStatus(404);
                outputJsonFormat.setMessageError(resp, "the currency from the currency pair does not exist");
            }

            if (exchangeRatesService.findCode(baseCurrency + targetCurrency)) {
                resp.setStatus(409);
                outputJsonFormat.setMessageError(resp, "exchange already exists");
            }else {
                UserAddExchangeRateDTO exchangeRatesDTO = new UserAddExchangeRateDTO(currenciesService.findByCode(baseCurrency), currenciesService.findByCode(targetCurrency), rate);
                exchangeRatesService.save(exchangeRatesDTO);
                outputJsonFormat.setMessageResult(resp, exchangeRatesDTO);
                resp.setStatus(201);
            }

        } catch (RuntimeException e) {
            outputJsonFormat.setMessageError(resp, "Error: " + e.getMessage());
            resp.setStatus(500);
        }


    }
}
