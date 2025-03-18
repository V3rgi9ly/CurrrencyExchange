package servlet;


import Service.ExchangeRatesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ExchangeRatesDTO;
import dto.UserAddExchangeRateDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/exchangeRate/*")
public class ServletExchangeRate extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final ExchangeRatesService exchangeRatesService = ExchangeRatesService.getInstance();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getMethod();
        if (method.equals("PATCH")) {
            this.doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getPathInfo().split("/");
        String code = requestURI[1];
        if (code.length() <= 5 || code.length() > 6) {
            throw new ServletException("Invalid request path");
        }
        PrintWriter out = resp.getWriter();
        ExchangeRatesDTO exchangeRatesDTO = ExchangeRatesService.getInstance().findByCode(code);
        if (exchangeRatesDTO == null) {
            throw new ServletException("Invalid request path");
        }

        String employeeJsonString = this.gson.toJson(exchangeRatesDTO);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();

    }


    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String[] requestURI = req.getPathInfo().split("/");
            String code = requestURI[1];
            String parameter = req.getParameter("rate");
            BigDecimal rate = BigDecimal.valueOf(Long.parseLong(parameter));

            if (code.length() <= 5 || code.length() > 6) {
                throw new ServletException("Invalid request path");
            }

            List<Object> element = new ArrayList<>();
            element.add(String.valueOf(rate));
            for (Object s : element) {
                if (s == null) {
                    throw new RuntimeException("params equals null or empty");
                }
            }

            PrintWriter out = resp.getWriter();
            ExchangeRatesDTO exchangeRatesDTO = exchangeRatesService.findByCode(code);
            if (exchangeRatesDTO == null) {
                throw new ServletException("Invalid request path");
            }
            exchangeRatesService.update(exchangeRatesDTO, rate);

            String employeeJsonString = this.gson.toJson(exchangeRatesDTO);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(201);
            out.print(employeeJsonString);
            out.flush();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
