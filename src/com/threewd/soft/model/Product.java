package com.threewd.soft.model;

public class Product {

    private int productId;
    private String productName;
    private Category category;  
    private Supplier supplier;   
    private int quantity;
    private double price;

    public Product() {}

    public Product(int productId, String productName, Category category,
                   Supplier supplier, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

