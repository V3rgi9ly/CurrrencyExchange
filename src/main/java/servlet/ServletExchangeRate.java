package servlet;


import com.google.gson.JsonArray;
import dao.ExchangeRatesDAO;
import jakarta.servlet.ServletConfig;
import service.ExchangeRatesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ExchangeRatesDTO;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.*;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/exchangeRate/*")
public class ServletExchangeRate extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final ExchangeRatesService exchangeRatesService = ExchangeRatesService.getInstance();
    private ExchangeRatesDAO exchangeRatesDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        exchangeRatesDAO = (ExchangeRatesDAO) config.getServletContext().getAttribute(ExchangeRatesDAO.class.getName());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("PATCH"))
            doPatch(req, resp);
        else
            super.service(req, resp);
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
        String e = req.getReader().readLine();
        String[] requestURI = req.getPathInfo().split("/");
        String code = requestURI[1];
        String[] request = e.split("=");
        String d = request[1];
        PrintWriter out = resp.getWriter();
        BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(d));

        if (code.length() <= 5 || code.length() > 6) {
            throw new ServletException("Invalid request path");
        }

        try {
            ExchangeRatesDTO exchangeRatesDTO = exchangeRatesService.findByCode(code);
            if (exchangeRatesDTO == null) {
                throw new ServletException("Invalid request path");
            }
            List<Object> element = new ArrayList<>();
            element.add(String.valueOf(rate));
            for (Object s : element) {
                if (s == null) {
                    throw new RuntimeException("params equals null or empty");
                }
            }

            exchangeRatesService.update(exchangeRatesDTO, rate);
            String employeeJsonString = this.gson.toJson(exchangeRatesService.findByCode(code));
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(201);
            out.print(employeeJsonString);
            out.flush();
            out.close();

        } catch (RuntimeException a) {
            throw new RuntimeException(a);
        }



    }
}
