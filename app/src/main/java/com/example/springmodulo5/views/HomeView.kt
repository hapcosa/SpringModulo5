package com.example.springmodulo5.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.springmodulo5.component.bottomAppBar

import com.example.springmodulo5.component.floatingButton
import com.example.springmodulo5.component.productCard
import com.example.springmodulo5.component.topBar
import com.example.springmodulo5.models.Product
import com.example.springmodulo5.viewmodels.ProductsViewModel
import java.lang.reflect.Modifier

@Composable
fun HomeView(navController: NavController, viewModel: ProductsViewModel){
    Scaffold(
        topBar={topBar("Tienda de Zapatos", navController)},
        content={ ContentHomeView(it, navController, viewModel) },
        bottomBar = { bottomAppBar(navController) },
    )


}
@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, viewModel:ProductsViewModel){
    val state = viewModel.state
    Text(text = "${state.listProducts.size}")
    Column (
        modifier = androidx.compose.ui.Modifier.padding(it)
    ){ LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalItemSpacing = 6.dp,
        modifier = androidx.compose.ui.Modifier.padding(6.dp),
        content={
            items(state.listProducts)
            {
                productCard(it, navController)
            }
        }
    )


    }
}





