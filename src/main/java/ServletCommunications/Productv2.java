package ServletCommunications;


public class Productv2 {
    public int id;
    public String name;
    public String description;
    public Double price;
    public Integer quantity;
    public String category;
    public Boolean limited;
    public String brand;
    public int change;
    public String saleLimit;
    public String amount;

    public Productv2(String name, String brand, int change){
        this.name = name;
        this.brand = brand;
        this.change = change;
    }
    public Productv2(String name, String brand, String saleLimit, Double price, String amount){
        this.name = name;
        this.brand = brand;
        this.saleLimit = saleLimit;
        this.price = price;
        this.amount = amount;
    }
    public Productv2(int barcode, String brand, String name, int quantity, String category){
        this.id = barcode;
        this.name = name;
        this.brand = brand;
        this.quantity = quantity;
        this.category = category;
    }

}
