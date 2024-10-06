package com.example.springmodulo5.models;

import androidx.compose.ui.graphics.Color;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"cartId","productId"})
public class CartProductRef {
    private int cartId;
    private int productId;
    private String talla;
    private String color;
    private int cantidad;

    public CartProductRef(){

    }
    public CartProductRef(int cartId, int productId, String talla, String color, int cantidad) {
        this.cartId = cartId;
        this.productId = productId;
        this.talla = talla;
        this.color = color;
        this.cantidad = cantidad;
    }



    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
