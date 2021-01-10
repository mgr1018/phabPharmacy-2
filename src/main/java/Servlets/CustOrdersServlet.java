
package Servlets;

import ServletCommunications.AccessCustOrders;
import ServletCommunications.*;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/custorders"},loadOnStartup = 1)
public class CustOrdersServlet extends HttpServlet {
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
        OnlineOrdersSize l = new OnlineOrdersSize();
        for(int i=1; i<=l.getSize(); i++){
            AccessCustOrders co = new AccessCustOrders(reqBody);
            Productv2 prod = new Productv2(Integer.valueOf(co.getBarcode()), co.getBrand(), co.getName(), Integer.valueOf(co.getQty()), co.getCategory());
            //Customer c = new Customer(onlineCustomer.getFirstName(),onlineCustomer.getLastName(), onlineCustomer.getPostcode(), onlineCustomer.getEmail(), onlineCustomer.getPostalAddress(), onlineCustomer.getContactNo());
            Gson gson = new Gson();
            String jsonString = gson.toJson(prod);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonString);
        }

    }

}