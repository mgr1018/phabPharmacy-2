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

public class ReturnCustOrders {
    private static final Logger log= Logger.getLogger(ReturnOrderedProduct.class.getName());
    public ArrayList<Productv2> custorders = new ArrayList<Productv2>();
    public String barcode;
    public String brand;
    public String name;
    public String qty;
    public String category;
    //float unitPrice;
    // public String amount;
    public ReturnCustOrders(int custId){
        String message = String.valueOf(custId);
        byte[] body = message.getBytes(StandardCharsets.UTF_8);
        URL myURL = null;
        try {
            myURL = new URL("https://phabbackend.herokuapp.com/custorders");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            log.info("RD: connection made");
// Set up the header
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
// Write the body of the request
            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body, 0, body.length);
            }
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
// Read the body of the response
            while ((inputLine = bufferedReader.readLine()) != null) {
                Gson gson = new Gson();
                Productv2 p=gson.fromJson(inputLine, Productv2.class);

                this.barcode = String.valueOf(p.id);
                this.brand = p.brand;
                this.name = p.name;
                this.qty = String.valueOf(p.quantity);
                this.category = p.category;
                //System.out.println(unitPrice);

                custorders.add(p);
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
    public String getBarcode() {
        return barcode;
    }
    public String getBrand() {
        return brand;
    }
    public String getName() {
        return name;
    }
    public String getQty() {
        return qty;
    }
    public String getCategory() {
        return category;
    }
    public ArrayList<Productv2> getCustorders(){
        return custorders;
    }
}
