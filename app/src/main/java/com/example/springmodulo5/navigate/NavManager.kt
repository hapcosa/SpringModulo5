package com.example.springmodulo5.navigate
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.springmodulo5.viewmodels.CartViewModel
import com.example.springmodulo5.viewmodels.ProductsViewModel
import com.example.springmodulo5.views.CartView
import com.example.springmodulo5.views.DetailView
import com.example.springmodulo5.views.HomeView

@Composable
fun NavManager(viewModel: ProductsViewModel,viewModelCart: CartViewModel){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "inicio"){
        composable("inicio"){
            HomeView(navController,viewModel)
        }
        composable("cart"){
            CartView(navController,viewModelCart,viewModel)
        }
        composable("detail/{id}", arguments = listOf(
            navArgument("id"){
                type= NavType.IntType
            })){
            DetailView(
                navController,
                viewModel,
                it.arguments!!.getInt("id"),viewModelCart
            )
        }
    }
}