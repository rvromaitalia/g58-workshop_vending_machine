package se.lexicon.model;

/**
 * <h2>Product</h2>
 * <h3>Abstract Class</h3>
 * responsibility for children which are products, this is the parent for the abstraction
 */
public abstract class   Product {
    private int id;
    private double price;
    private String productName;

    public Product(int id, double price, String productName){
        this.id = id;
        this. price = price;
        this.productName = productName;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price<=0)
            throw new IllegalArgumentException("Price can not be negative or 0");
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if(productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name can not be null or blank");
        }
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String use();

    /**
     *
     */
    public abstract String examine();
}





