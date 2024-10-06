package com.example.springmodulo5.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springmodulo5.R
import com.example.springmodulo5.models.Product
import com.example.springmodulo5.room.ProductosDateBaseDao
import com.example.springmodulo5.state.ProductState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val dao:ProductosDateBaseDao
): ViewModel() {
    var state by mutableStateOf(ProductState())
        private set
    init{
        viewModelScope.launch {
            if(!dao.isExists()){
                var product: Product = Product("Bototo de marca","bototo anti agua",70000.0,6, R.drawable.th_1763182607)
                dao.insert(product)
               var product2 = Product("Zapatillas deportivas", "zapatillas de correr", 34000.0, 3, R.drawable.th_3625144094)
                dao.insert(product2)
                var product3 = Product("Zapatillas deportivas2", "zapatillas de correr", 34000.0, 3, R.drawable.th_3625144094)
                dao.insert(product3)

            }

            dao.getProduct().collectLatest {
                state = state.copy(
                    listProducts =  it
                )
            }




        }
    }
    fun addProduct(product: Product) = viewModelScope.launch {
        dao.insert(product = product)
    }
    fun getProductId(id:Int): Product {
        var product:Product=Product()
        viewModelScope.launch {
           product= dao.getProductById(id.toLong())
        }
        return product

    }

}