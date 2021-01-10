package ServletCommunications;

import java.sql.*;

public class AccessCustOrders {
    //ArrayList <Product> custorders = new ArrayList<Product>();
    public String barcode;
    public String brand;
    public String name;
    public String qty;
    public String category;
    public AccessCustOrders(String CustomerId){
        int intId = Integer.parseInt(CustomerId);
        System.out.println(intId);
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            String sqlStr = "SELECT * FROM ordered_product WHERE customerid = " + intId;
            System.out.println(sqlStr);
            ResultSet rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                this.barcode = barcode;
                this.brand = rs.getString("brand");
                this.name = rs.getString("name");
                this.qty = rs.getString("quantity");
                this.category = rs.getString("category");

            }
            rs.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
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
}
