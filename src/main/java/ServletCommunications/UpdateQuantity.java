package ServletCommunications;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateQuantity {
    public int quant;
    private int fullStock;
    private float sellPrice;
    private float buyPrice;

    public UpdateQuantity(String name1, String brand1, int change) {
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
                sellPrice = rs.getFloat("sell_price");
                buyPrice = rs.getFloat("buy_price");
                fullStock = rs.getInt("full_stock");
                System.out.println(quant);
            }
            if (change < 0) {
                float oldProfit = 0;
                float newProfit = 0;
                rs = stmt.executeQuery("SELECT * FROM daily_profit WHERE date = CURRENT_DATE ;");
                if (rs.next()) {
                    oldProfit = rs.getFloat("profit");
                }
                else {
                    oldProfit = 0;
                    stmt.execute("INSERT INTO daily_profit(date, profit) VALUES(CURRENT_DATE,0);");
                }
                float saleProfit = -change * (sellPrice - buyPrice);
                newProfit = oldProfit + saleProfit;
                stmt.execute("UPDATE daily_profit SET profit = " + newProfit + " WHERE date = CURRENT_DATE;");
            }
            int update = quant + change;
            stmt.execute("UPDATE shop_product SET quantity = " + update + " WHERE name = " + name1 + " AND brand = " + brand1 + ";");
            int hardmin = (int) (fullStock*0.1);
            if(update<hardmin){
                List<Product> order = new ArrayList<>();
                //order.add(new Product(name1, brand1, fullStock-quant));
                //we want to check all other members below their softmins e.g fullstock*0.2 and make order
                ResultSet rset=stmt.executeQuery("SELECT * FROM shop_product WHERE id>0 ");
                while(rset.next()){
                    int fullStock1 = (rset.getInt("full_stock"));
                    int quantity = rset.getInt("quantity");
                    String brand2 = rset.getString("brand");
                    String name2 = rset.getString("name");
                    float unitprice2 = rset.getFloat("buy_price");
                    int change2 = fullStock1 - quantity;
                    if(quantity < (fullStock1*0.2)){
                        order.add(new Product(name2, brand2, unitprice2,change2));
                        System.out.println(name2);
                    }
                }
                //output the orders list to the wholesaler email
                MailSender newMail = new MailSender(order);
                //transfer required money
                //At the moment bank server seems to be down, but once fixed, transactions will be automatic
                //with code below:
                //Transaction newTransaction = new Transaction(order);
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
