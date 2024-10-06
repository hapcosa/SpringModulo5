package com.example.springmodulo5.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class productencarrito {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int idproducto;
    public String talla;
    public String Color;
    public int cantidad;

}
