package org.persistence.entities;// default package
// Generated Mar 14, 2023, 1:07:33 AM by Hibernate Tools 6.1.7.Final


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Cartitems generated by hbm2java
 */
@Entity
@Table(name="cartitems"
    ,catalog="talktech"
)
public class CartItems implements java.io.Serializable {


     private CartitemsId id;
     private Product product;
     private Cart cart;
     private Integer quantity;

    public CartItems() {
    }

	
    public CartItems(CartitemsId id, Product product, Cart cart) {
        this.id = id;
        this.product = product;
        this.cart = cart;
    }
    public CartItems(CartitemsId id, Product product, Cart cart, Integer quantity) {
       this.id = id;
       this.product = product;
       this.cart = cart;
       this.quantity = quantity;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="cartId", column=@Column(name="CartID", nullable=false) ), 
        @AttributeOverride(name="productId", column=@Column(name="ProductID", nullable=false) ) } )
    public CartitemsId getId() {
        return this.id;
    }
    
    public void setId(CartitemsId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ProductID", nullable=false, insertable=false, updatable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CartID", nullable=false, insertable=false, updatable=false)
    public Cart getCart() {
        return this.cart;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    
    @Column(name="Quantity")
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




}


