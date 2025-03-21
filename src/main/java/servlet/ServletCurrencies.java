package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import service.CurrenciesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CurrenciesDTO;
import dto.UserAddCurrencyDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@WebServlet("/currencies")
public class ServletCurrencies extends HttpServlet {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CurrenciesService currenciesService = CurrenciesService.getInstance();



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<CurrenciesDTO> currenciesDTO = currenciesService.findAll();
        String employeeJsonString = this.gson.toJson(currenciesDTO);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
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
                throw new RuntimeException("params equals null or empty");
            }
        }

        if (code!=code.toUpperCase()){
            throw new RuntimeException("params not equals");
        }

        if (currenciesService.findCode(code)){
            throw new RuntimeException("code already exists");
        }

        PrintWriter out = response.getWriter();

        UserAddCurrencyDTO userAddCurrencyDTO = new UserAddCurrencyDTO(code, fullName, sign);
        currenciesService.save(userAddCurrencyDTO);
        String employeeJsonString = this.gson.toJson(userAddCurrencyDTO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(201);
        out.print(employeeJsonString);
        out.flush();

    }
}
