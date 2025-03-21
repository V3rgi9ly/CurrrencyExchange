package servlet;


//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;


import service.CurrenciesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CurrenciesDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor

@WebServlet("/currencies/*")
public class ServletCurrency extends HttpServlet {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CurrenciesService currencyService = CurrenciesService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] requestURI = request.getPathInfo().split("/");
        String code=requestURI[1];
        if (code.length()<=2 || code.length()>3) {
            throw new ServletException("Invalid request path");
        }
        PrintWriter out = response.getWriter();
        CurrenciesDTO currenciesDTO = currencyService.findByCode(code);
        if (currenciesDTO == null) {
            throw new ServletException("Currency not found");
        }

        String employeeJsonString = this.gson.toJson(currenciesDTO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();

    }
}
