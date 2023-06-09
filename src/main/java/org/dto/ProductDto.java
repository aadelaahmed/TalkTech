package org.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Integer productId;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private String category;
    private String color;
    private String imageUrl;
    public ProductDto(){

    }

    public ProductDto(Integer productId, String name,String imageUrl, String description, String brand, BigDecimal price, Integer quantity, String category, String color) {
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
