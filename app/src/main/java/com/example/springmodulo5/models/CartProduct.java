package com.example.springmodulo5.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;


public class CartProduct{
    @Embedded private Cart cart;
    @Relation(parentColumn = "cartId",entityColumn = "productId",
            associateBy = @Junction(CartProductRef.class)
    )
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
