package com.example.springmodulo5.room

import androidx.room.Database

import androidx.room.RoomDatabase
import com.example.springmodulo5.models.Cart
import com.example.springmodulo5.models.CartProductRef

import com.example.springmodulo5.models.Product


@Database(entities = [Product::class,Cart::class,CartProductRef::class], version=1, exportSchema = false)
abstract class ProductosDateBase:RoomDatabase() {
    abstract fun productDao():ProductosDateBaseDao
    abstract fun CartDao():CartDao
    abstract fun CartProductDao():CartProductDao
}