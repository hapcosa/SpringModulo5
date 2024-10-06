package com.example.springmodulo5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.springmodulo5.navigate.NavManager
import com.example.springmodulo5.room.ProductosDateBase
import com.example.springmodulo5.ui.theme.SpringModulo5Theme
import com.example.springmodulo5.viewmodels.CartViewModel
import com.example.springmodulo5.viewmodels.ProductsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpringModulo5Theme(isSystemInDarkTheme(),false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.inversePrimary
                ) {
                    val database = Room.databaseBuilder(this, ProductosDateBase::class.java,"db_product").allowMainThreadQueries().build()
                    val dao =database.productDao()
                    val daoCart = database.CartDao()
                    val daoProductCart=database.CartProductDao()
                    val viewModel=ProductsViewModel(dao)
                    val viewModelCart =CartViewModel(daoCart,daoProductCart)
                    NavManager(viewModel,viewModelCart)
                }
            }
        }
    }
}

