package com.example.springmodulo5.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.springmodulo5.component.ShoppingCartProductItem
import com.example.springmodulo5.component.bottomAppBar
import com.example.springmodulo5.component.floatingButton
import com.example.springmodulo5.component.topBar
import com.example.springmodulo5.models.Product
import com.example.springmodulo5.viewmodels.CartViewModel
import com.example.springmodulo5.viewmodels.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(navController: NavController,viewModel: CartViewModel,viewModelProduct: ProductsViewModel) {
    var contador = viewModel.state.listCartProducts.size
    Scaffold(

            topBar = { topBar("Carrito", navController )},
            content = {Column {
                Text(text = "Productos: $contador")
                ContentCartView(it, navController, viewModel , viewModelProduct)
            }},
            bottomBar = { bottomAppBar(navController )}
            )

}

@Composable
fun ContentCartView(
        it: PaddingValues,
        navController: NavController,
        viewModel: CartViewModel,
        viewModelProduct: ProductsViewModel
    ) {
        viewModel.getProductCart()
        val state = viewModelProduct.state
        val cartState = viewModel.state
        if(cartState.listCartProducts.isEmpty()){
            Log.e("MyApp", "${cartState.listCartProducts.size}")
        }
       else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(cartState.listCartProducts) { productCart ->

                    ShoppingCartProductItem(viewModelProduct.getProductId(productCart.productId)
                        ,
                        navController,productCart,viewModel
                    )
                }
            }
        }
}

