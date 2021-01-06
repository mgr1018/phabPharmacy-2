package ServletCommunications;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
/* This class takes the id/barcode of a product in its constructor and then makes
* a post request to the access servlet which responds with the details of that
* product from the database. Logs beginning with RD come from this class */
public class ReturnDetails {
    public String name;
    public String brand;
    public String saleLimit;
    float unitPrice;
    public String amount;
    public ReturnDetails(int id){
        String message = String.valueOf(id);
        byte[] body = message.getBytes(StandardCharsets.UTF_8);
        URL myURL = null;
        try {
            myURL = new URL("https://phabbackend.herokuapp.com/details");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            System.out.println("RD: connection made");
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
                Product p=gson.fromJson(inputLine, Product.class);
                this.name = p.name;
                this.brand = p.brand;
                this.saleLimit = p.saleLimit;
                this.unitPrice = p.unitPrice;
                this.amount = p.amount;
                System.out.println(name);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            System.out.println("RD: problem with URL");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            System.out.println("RD: protocol error, such as problem with TCP");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("RD: an i/o error has occured");
            e.printStackTrace();
        }

    }
    public String getName() {
        return name;
    }
    public String getBrand() {
        return brand;
    }
    public String getSaleLimit() {
        return saleLimit;
    }
    public float getUnitPrice() {
        return unitPrice;
    }
    public String getAmount() {
        return amount;
    }

}

