package com.example.springmodulo5.state

import com.example.springmodulo5.component.productCard
import com.example.springmodulo5.models.Cart
import com.example.springmodulo5.models.CartProduct
import com.example.springmodulo5.models.CartProductRef

data class CartState(val listCartProducts: List<CartProductRef> = emptyList(),
                     val cart : Cart = Cart()
)
