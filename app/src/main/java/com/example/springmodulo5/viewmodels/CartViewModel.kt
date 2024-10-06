package com.example.springmodulo5.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.springmodulo5.models.Cart
import com.example.springmodulo5.models.CartProduct
import com.example.springmodulo5.models.CartProductRef
import com.example.springmodulo5.models.Product
import com.example.springmodulo5.room.CartDao
import com.example.springmodulo5.room.CartProductDao
import com.example.springmodulo5.state.CartState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class CartViewModel(private val dao: CartDao, private val daoCartProduct: CartProductDao): ViewModel() {

    var state: CartState by mutableStateOf(CartState())
        private set
    init {
        viewModelScope.launch {
            if (!dao.isExists()) {
                createCart()

            }

            dao.getCartById(1).collect() {
                state = state.copy(
                    cart = it
                )
            }

            if (daoCartProduct.isExists()) {
                state = state.copy(
                    listCartProducts = daoCartProduct.getProductsCart(state.cart.cartId)
                )

            }


        }


    }
        fun createCart() = viewModelScope.launch {
            val cart = Cart()
            dao.insert(cart)
        }
        fun getProductCart() = viewModelScope.launch {
            state = state.copy(
                listCartProducts = dao.getProductsCart(state.cart.cartId)
            )
        }
        fun addToCart(product: Product, talla: String, color: String) = viewModelScope.launch {
            Log.i("MyApp", "se añadio al carrito el color: $color")
            val cartp: CartProductRef =
                CartProductRef(state.cart.cartId, product.productId, talla,color, 1)
            try {
                daoCartProduct.insert(cartp)
                Log.i("MyApp", "se añadio al carrito: ${state.cart.cartId}")
            } catch (e: Exception) {
                Log.i("MyApp", "NO se añadio al carrito: ${e.message}")
            }

        }
        fun deleteCartProduct(cartProductRef: CartProductRef) = viewModelScope.launch {
            daoCartProduct.delete(cartProductRef)
            state = state.copy(
                listCartProducts = dao.getProductsCart(state.cart.cartId)
            )
        }

    }