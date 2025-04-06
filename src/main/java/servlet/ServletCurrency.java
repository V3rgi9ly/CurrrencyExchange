package servlet;

import Config.OutputJsonFormat;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import service.CurrenciesService;
import dto.CurrenciesDTO;

@WebServlet("/currencies/*")
public class ServletCurrency extends HttpServlet {

    private final CurrenciesService currencyService = new CurrenciesService();
    private final OutputJsonFormat outputJsonFormat = new OutputJsonFormat();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] requestURI = request.getPathInfo().split("/");
            String code = requestURI[1];
            if (code.length() <= 2 || code.length() > 3) {
                response.setStatus(400);
                outputJsonFormat.setMessageError(response, "Invalid request path");
            }

            CurrenciesDTO currenciesDTO = currencyService.findByCode(code);
            if (currenciesDTO.getId() == 0) {
                response.setStatus(404);
                outputJsonFormat.setMessageError(response, "Currencies not found");
            } else {
                outputJsonFormat.setMessageResult(response, currenciesDTO);
                response.setStatus(200);
            }
        } catch (RuntimeException e) {
            outputJsonFormat.setMessageError(response, "Error: " + e.getMessage());
            response.setStatus(500);
        }
    }
}
