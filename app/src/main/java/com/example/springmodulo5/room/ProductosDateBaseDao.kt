package com.example.springmodulo5.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.springmodulo5.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDateBaseDao {
    @Query("SELECT EXISTS(SELECT * FROM Product)")
    fun isExists(): Boolean

    @Query("SELECT * FROM Product")
    fun getProduct(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE productId = :id")
    fun getProductById(id: Long): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}