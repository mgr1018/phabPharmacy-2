package Servlets;

import ServletCommunications.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.lang.*;

@WebServlet(urlPatterns={"/checkoff"},loadOnStartup = 1)
public class CheckoffServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //SqlSetup tables = new SqlSetup();
        //SelectProduct quantity = new SelectProduct("'4 flu'", "'Benylin'");
        //resp.getWriter().write("<html> <head> <title>CMDMC</title> </head><body> <h1>Value Accessed " + quantity.quant + "</h1> </body> </html>");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        Productv2 p=gson.fromJson(reqBody, Product.class);
        UpdateQuantity update = new UpdateQuantity(p.name, p.brand, p.change);
        SelectProduct quantity = new SelectProduct(p.name, p.brand);
        resp.setContentType("text/html");
        String quant = new String(String.valueOf(quantity.quant));
        resp.getWriter().write(quant);
    }
}
