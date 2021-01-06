package ServletCommunications;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
/* This class takes the name, brand and change in quantity of a product and updates the quantity
* of the corresponding product entry in the pharmacy database. It makes a single post
* request to the update servlet. Logs beginning with UQ come from this class  */
public class UpdateQuant {
    public UpdateQuant(String name, String brand, int change){
        Product p = new Product(name, brand, change);
        Gson gson = new Gson();
        String jsonString = gson.toJson(p);
        String message = jsonString;
        byte[] body = message.getBytes(StandardCharsets.UTF_8);
        URL myURL = null;
        try {
            myURL = new URL("https://phabbackend.herokuapp.com/update");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            System.out.println("UQ: connection made");
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
                System.out.println(inputLine);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            System.out.println("UQ: problem with URL");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("UQ: an i/o error has occured when connecting to the URL");
            e.printStackTrace();
        } catch (ProtocolException e) {
            System.out.println("UQ: protocol error, such as problem with TCP");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("UQ: an i/o error has occured");
            e.printStackTrace();
        }

    }
}
