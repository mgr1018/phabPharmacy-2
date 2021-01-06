package ServletCommunications;

import java.io.Serializable;
/* This class contains the various constructors required by different classes.
* The class itself can be instantiated with values of the real world products
* at the pharmacy*/
public class Product implements Serializable {
    public String name;
    public String brand;
    public int change;
    String saleLimit;
    float unitPrice;
    public String amount;
    int quantity;

    public Product(String name, String brand, int change) {
        this.name = name;
        this.brand = brand;
        this.change = change;
    }

    public Product(String name, String brand, String saleLimit, float unitPrice, String amount) {
        this.name = name;
        this.brand = brand;
        this.saleLimit = saleLimit;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public Product(String name, String brand, float unitPrice, int quantity) {
        this.name = name;
        this.brand = brand;
        this.quantity = quantity;
        this.unitPrice = unitPrice;

    }
    public String getName() {
        return name;
    }
    public String getBrand() {
        return brand;
    }
    public int getChange() {
        return change;
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
    public int getQuantity() {
        return quantity;
    }

}
