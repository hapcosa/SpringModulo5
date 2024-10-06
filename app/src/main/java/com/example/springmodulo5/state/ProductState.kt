package com.example.springmodulo5.state

import com.example.springmodulo5.models.Product

data class ProductState (
    val listProducts:List<Product> = emptyList(),
    val product: Product = Product()
)