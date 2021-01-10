package ServletCommunications;

import java.sql.*;

public class SelectProduct{
    public int quant;
    public SelectProduct(String name1, String brand1){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            String sqlStr = "SELECT * FROM shop_product WHERE name = " + name1 + " AND brand = " + brand1;
            System.out.println(sqlStr);
            ResultSet rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                quant = rs.getInt("quantity");
                System.out.println(quant);
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}