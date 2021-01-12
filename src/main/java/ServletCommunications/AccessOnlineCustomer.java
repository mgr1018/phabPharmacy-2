package ServletCommunications;

import java.sql.*;

public class AccessOnlineCustomer {
    public String CID;
    public String first_name;
    public String last_name;
    public String contact_no;
    public String postal_address;
    public String email;
    public String postcode;
    public Boolean pendingorder;
    public AccessOnlineCustomer(String id){
        pendingorder = false;
        int intId = Integer.parseInt(id);
        System.out.println(intId);
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            Statement stmt2 = db.createStatement();
            String sqlStr = "SELECT * FROM customer WHERE id = " + intId;
            String sqlStr2 = "SELECT * FROM ordered_product WHERE customerid = " + intId;
            System.out.println(sqlStr);
            ResultSet rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                this.first_name = rs.getString("first_name");
                this.last_name = rs.getString("brand");
                this.CID = id;
                this.contact_no = rs.getString("phone_no");
                this.postal_address = rs.getString("address");
                this.postcode = rs.getString("postcode");
                this.email = rs.getString("email");
            }
            ResultSet rs2 = stmt2.executeQuery(sqlStr2);
            if(rs2.next()){
                this.pendingorder = true;
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public String getFirstName() {
        return first_name;
    }
    public String getLastName() {
        return last_name;
    }
    public String getPostalAddress() {
        return postal_address;
    }
    public String getCID() {
        return CID;
    }
    public String getContactNo() {
        return contact_no;
    }
    public String getEmail() {
        return email;
    }
    public String getPostcode() {
        return postcode;
    }
    public Boolean getPendingorder(){return pendingorder;}
}