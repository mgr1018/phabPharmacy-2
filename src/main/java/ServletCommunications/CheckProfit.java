package ServletCommunications;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
/*  This class takes a date in it's constructor in order to perform the servlet
    communication required to check the profit made at that date.
    It makes a single post to the accessProfit servlet, the response to which
    is the profit. Logs beginning with CP come from this class */
public class CheckProfit {
    public float profit;
    public CheckProfit(String date){
        String message = date;
        byte[] body = message.getBytes(StandardCharsets.UTF_8);
        URL myURL = null;
        try {
            myURL = new URL("https://phabbackend.herokuapp.com/accessProfit");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            System.out.println("CP: connection made");
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
                this.profit = Float.parseFloat(inputLine);
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
    public float getProfit() {
        return profit;
    }
}
