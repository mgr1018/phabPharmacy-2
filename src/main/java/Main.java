import ClientPages.LoginPage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args){
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            System.out.println("File Logging Error");
            e.printStackTrace();
        }
        LoginPage login = new LoginPage();
    }
}
