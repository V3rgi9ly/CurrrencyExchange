package servlet;

import Config.OutputJsonFormat;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import service.CurrenciesService;
import dto.CurrenciesDTO;

@WebServlet("/currencies")
public class ServletCurrencies extends HttpServlet {


    private final CurrenciesService currenciesService = new CurrenciesService();
    private final OutputJsonFormat outputJsonFormat = new OutputJsonFormat();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CurrenciesDTO> currenciesDTO = currenciesService.findAll();
            outputJsonFormat.setMessageResult(response, currenciesDTO);
            response.setStatus(200);

        } catch (RuntimeException e) {
            String message = "Error: " + e.getMessage();
            outputJsonFormat.setMessageError(response, message);
            response.setStatus(500);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String fullName = request.getParameter("fullname");
        String sign = request.getParameter("sign");

        List<String> element = new ArrayList<>();
        element.add(code);
        element.add(fullName);
        element.add(sign);

        try {
            for (String s : element) {
                if (s == null || s.isEmpty()) {
                    outputJsonFormat.setMessageError(response, "params equals null or empty");
                    response.setStatus(400);
                }
            }

            if (code != code.toUpperCase()) {
                outputJsonFormat.setMessageError(response, "params not equals");
                response.setStatus(400);
            }

            if (currenciesService.findCode(code)) {
                outputJsonFormat.setMessageError(response, "code already exists");
                response.sendError(409);
            }else {
                currenciesService.save(code, fullName, sign);
                CurrenciesDTO currenciesDTO = currenciesService.findByCode(code);
                outputJsonFormat.setMessageResult(response, currenciesDTO);
                response.setStatus(201);
            }
        } catch (IOException e) {
            String message = "Error: " + e.getMessage();
            outputJsonFormat.setMessageError(response, message);
            response.setStatus(500);
        }
    }
}
