package servlet;

import java.io.IOException;

import Config.OutputJsonFormat;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import service.CurrenciesService;
import dto.CurrenciesDTO;
import dto.UserAddCurrencyDTO;

@WebServlet("/currencies")
public class ServletCurrencies extends HttpServlet {


    private final CurrenciesService currenciesService = CurrenciesService.getInstance();
    private final OutputJsonFormat outputJsonFormat = new OutputJsonFormat();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try{
            List<CurrenciesDTO> currenciesDTO = currenciesService.findAll();
            outputJsonFormat.setMessageResult(response, currenciesDTO);
            response.setStatus(200);

        }catch (RuntimeException e) {
            String message = "Error: " + e.getMessage();
            outputJsonFormat.setMessageError(response, message);
            response.setStatus(500);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String fullName = request.getParameter("fullName");
        String sign = request.getParameter("sign");

        List<String> element=new ArrayList<>();
        element.add(code);
        element.add(fullName);
        element.add(sign);
        for (String s : element) {
            if(s==null || s.isEmpty()){
                outputJsonFormat.setMessageError(response, "params equals null or empty");
                response.setStatus(400);
            }
        }

        if (code!=code.toUpperCase()){
            outputJsonFormat.setMessageError(response, "params not equals");
            response.setStatus(400);
        }

        if (currenciesService.findCode(code)){
            outputJsonFormat.setMessageError(response, "code already exists");
            response.setStatus(409);
        }

        try {
            UserAddCurrencyDTO userAddCurrencyDTO = new UserAddCurrencyDTO(code, fullName, sign);
            currenciesService.save(userAddCurrencyDTO);
            outputJsonFormat.setMessageResult(response, userAddCurrencyDTO);
            response.setStatus(201);
        }catch (RuntimeException e) {
            outputJsonFormat.setMessageError(response, "Error: " + e.getMessage());
            response.setStatus(500);
        }



    }
}
