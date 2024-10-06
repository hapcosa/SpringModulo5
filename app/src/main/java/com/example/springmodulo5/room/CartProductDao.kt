package com.example.springmodulo5.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.springmodulo5.models.Cart
import com.example.springmodulo5.models.CartProduct
import com.example.springmodulo5.models.CartProductRef
import kotlinx.coroutines.flow.Flow
@Dao
interface CartProductDao {
    @Query("SELECT EXISTS(SELECT * FROM CartProductRef)")
    fun isExists(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartProduct: CartProductRef)
    @Delete
    fun delete(CartProduct: CartProductRef)
    @Query("SELECT * FROM CartProductRef WHERE cartId=:id")
    fun getProductsCart(id: Int) : List<CartProductRef>
}
