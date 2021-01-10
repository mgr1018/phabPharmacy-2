package ServletCommunications;
import java.sql.*;

public class OnlineOrdersSize {

    public OnlineOrdersSize(){}

    public int getSize(){
        int size = 0;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            String sqlStr = "SELECT count(*) FROM ordered_product";
            ResultSet rs = stmt.executeQuery(sqlStr);
            while (rs.next()){
                size= rs.getInt(1);
            }
            stmt.close();
            rs.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return size;
    }


}
