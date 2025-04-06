package servlet;


import Config.OutputJsonFormat;
import service.ExchangeRatesService;
import dto.ExchangeRatesDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/exchangeRate/*")
public class ServletExchangeRate extends HttpServlet {

    private final ExchangeRatesService exchangeRatesService = new ExchangeRatesService();
    private final OutputJsonFormat outputJsonFormat = new OutputJsonFormat();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("PATCH"))
            doPatch(req, resp);
        else
            super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String[] requestURI = req.getPathInfo().split("/");
        String code = requestURI[1];

        try {
            if (code.length() <= 5 || code.length() > 6) {
                resp.setStatus(400);
                outputJsonFormat.setMessageError(resp, "Invalid request path");
            }

            ExchangeRatesDTO exchangeRatesDTO = exchangeRatesService.findByCode(code);
            if (exchangeRatesDTO.getId() == 0) {
                resp.setStatus(404);
                outputJsonFormat.setMessageError(resp, "Invalid request path");
            }else {
                outputJsonFormat.setMessageResult(resp, exchangeRatesDTO);
                resp.setStatus(200);
            }

        } catch (RuntimeException e) {
            outputJsonFormat.setMessageError(resp, "Error: " + e.getMessage());
            resp.setStatus(500);
        }

    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String e = req.getReader().readLine();
            String[] requestURI = req.getPathInfo().split("/");
            String code = requestURI[1];
            String[] request = e.split("=");
            String d = request[1];
            BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(d));

            if (code.length() <= 5 || code.length() > 6) {
                resp.setStatus(400);
                outputJsonFormat.setMessageError(resp, "Invalid request path");

            }

            ExchangeRatesDTO exchangeRatesDTO = exchangeRatesService.findByCode(code);
            if (exchangeRatesDTO == null) {
                resp.setStatus(404);
                outputJsonFormat.setMessageError(resp, "the currency from the currency pair does not exist");

            }
            List<Object> element = new ArrayList<>();
            element.add(String.valueOf(rate));
            for (Object s : element) {
                if (s == null) {
                    resp.setStatus(400);
                    outputJsonFormat.setMessageError(resp, "params equals null or empty");
                }
            }

            exchangeRatesService.update(exchangeRatesDTO, rate);
            outputJsonFormat.setMessageResult(resp, exchangeRatesService.findByCode(code));
            resp.setStatus(201);

        } catch (RuntimeException a) {
            outputJsonFormat.setMessageError(resp, "Error: " + a.getMessage());
            resp.setStatus(500);
        } catch (IOException e) {
            outputJsonFormat.setMessageError(resp, "Error: " + e.getMessage());
            resp.setStatus(500);
        }

    }
}
