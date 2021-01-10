package ServletCommunications;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;

/* This class makes a post request to the AccessCustomer servlet which responds with
 * the details of that online customer from the database. Logs beginning with RD come
 * from this class */

public class ReturnCustomers {
    private static final Logger log= Logger.getLogger(ReturnCustomers.class.getName());
    public ArrayList<Customer> customers = new ArrayList<Customer>();
    public String CID;
    public String first_name;
    public String last_name;
    public String contact_no;
    public String postal_address;
    //float unitPrice;
    // public String amount;
    public ReturnCustomers(){
        //String message = String.valueOf(id);
        //byte[] body = message.getBytes(StandardCharsets.UTF_8);
        URL myURL = null;
        try {
            myURL = new URL("https://phabbackend.herokuapp.com/customersdeets");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            log.info("RD: connection made");
// Set up the header
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            //conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
// Write the body of the request
            //try (OutputStream outputStream = conn.getOutputStream()) {
              //  outputStream.write(body, 0, body.length);
           // }
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
// Read the body of the response
            while ((inputLine = bufferedReader.readLine()) != null) {
                Gson gson = new Gson();
                Customer c = gson.fromJson(inputLine, Customer.class);
                //Product p=gson.fromJson(inputLine, Product.class);

                this.CID = String.valueOf(c.id);
                this.first_name = c.first_name;
                this.last_name = c.last_name;
                this.contact_no = c.phone_number;
                this.postal_address = c.postcode;

                customers.add(c);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            log.severe("RD: problem with URL");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            log.severe("RD: protocol error, such as problem with TCP");
            e.printStackTrace();
        } catch (IOException e) {
            log.severe("RD: an i/o error has occured");
            e.printStackTrace();
        }

    }
    public String getFirstName(int i) {
        return customers.get(i).first_name;
    }
    public String getLastName(int i) {
        return customers.get(i).last_name;
    }
    public String getPostalAddress(int i) {
        return customers.get(i).postcode;
    }
    public String getCID(int i) {
        return String.valueOf(customers.get(i).id);
    }
    public String getContactNo(int i) {
        return customers.get(i).phone_number;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}

