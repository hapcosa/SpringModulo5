package com.example.springmodulo5.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Product")
public class Product{
    @PrimaryKey(autoGenerate = true)
    private int productId;

    @ColumnInfo(name = "Nombre")
    private String name;

    @ColumnInfo(name = "Descripcion")
    private String desc;

    @ColumnInfo(name = "Precio")
    private Double price;

    @ColumnInfo(name = "Cantidad")
    private int count;

    @ColumnInfo(name = "ImageUrl")
    private int imageUrl;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product(){

    }
    public Product(String name, String desc, Double price, int count, int imageUrl) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.count = count;
        this.imageUrl = imageUrl;
    }
}
