package ServletCommunications;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class UpdateOrderDB {

    private static final Logger log= Logger.getLogger(UpdateOrderDB.class.getName());

    public UpdateOrderDB(int id){
    String message = String.valueOf(id);
    byte[] body = message.getBytes(StandardCharsets.UTF_8);
    URL myURL = null;
    try {
        myURL = new URL("https://phabbackend.herokuapp.com/checkoff");
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
//        while ((inputLine = bufferedReader.readLine()) != null) {
//            Gson gson = new Gson();
//            Product p=gson.fromJson(inputLine, Product.class);
//            this.name = p.name;
//            this.brand = p.brand;
//            this.saleLimit = p.saleLimit;
//            this.unitPrice = p.unitPrice;
//            this.amount = p.amount;
//            System.out.println(unitPrice);
//        }
//        bufferedReader.close();

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
}
