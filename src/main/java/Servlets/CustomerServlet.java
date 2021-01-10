package Servlets;

import ServletCommunications.*;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/customerdeets"},loadOnStartup = 1)
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //SqlSetup tables = new SqlSetup();
        //SelectProduct quantity = new SelectProduct("'4 flu'", "'Benylin'");
        //resp.getWriter().write("<html> <head> <title>CMDMC</title> </head><body> <h1>Value Accessed " + "i"+ "</h1> </body> </html>");
        resp.getWriter().write(req.getServletPath());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Gson gson = new Gson();
        AccessOnlineCustomer onlineCustomer = new AccessOnlineCustomer(reqBody);
        Customer c = new Customer(onlineCustomer.getFirstName(),onlineCustomer.getLastName(), onlineCustomer.getPostcode(), onlineCustomer.getEmail(), onlineCustomer.getPostalAddress(), onlineCustomer.getContactNo());
        String jsonString = gson.toJson(c);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonString);
    }

}
