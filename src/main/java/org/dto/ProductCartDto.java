package org.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductCartDto implements Serializable {
    Integer productId;
    String name;
    String brand;
    String imageUrl;
    int qtyInCart;
    int qtyInStock;
    BigDecimal price;

    public ProductCartDto() {

    }


    @Override
    public String toString() {
        return "{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", qtyInCart=" + qtyInCart +
                ", qtyInStock=" + qtyInStock +
                ", price=" + price +
                '}';
    }

    public ProductCartDto(Integer productId, String name, String brand, String imageUrl, int qtyInCart, int qtyInStock, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.qtyInCart = qtyInCart;
        this.qtyInStock = qtyInStock;
        this.price = price;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQtyInCart() {
        return qtyInCart;
    }

    public void setQtyInCart(int qtyInCart) {
        this.qtyInCart = qtyInCart;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
