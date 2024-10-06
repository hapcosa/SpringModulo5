package com.example.springmodulo5.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.springmodulo5.models.Cart
import com.example.springmodulo5.models.CartProduct
import com.example.springmodulo5.models.CartProductRef
import kotlinx.coroutines.flow.Flow
@Dao
interface CartDao {
    @Transaction
    @Query("SELECT * From Cart WHERE cartId=:id")
    fun getCartWithProducts(id:Int): Flow<CartProduct>

    @Query("SELECT * FROM CartProductRef WHERE cartId=:id")
    fun getProductsCart(id: Int) : List<CartProductRef>

    @Query("SELECT EXISTS(SELECT * FROM Cart)")
    fun isExists(): Boolean

    @Query("SELECT * FROM Cart")
    fun getCart(): Cart

    @Query("SELECT * FROM Cart WHERE cartId = :id")
    fun getCartById(id: Long): Flow<Cart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: Cart)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(cart: Cart)

    @Delete
    suspend fun delete(cart: Cart)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductCart(cartp: CartProductRef)
}