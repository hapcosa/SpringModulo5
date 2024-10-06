package com.example.springmodulo5.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Cart")
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int cartId;
    @ColumnInfo(name="Total")
    private Double total;
    public int getCartId() {
        return cartId;
    }
    public void setCartId(int id) {
        this.cartId = id;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {this.total = total;}

}

