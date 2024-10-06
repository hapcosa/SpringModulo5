package com.example.springmodulo5.views


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import com.example.springmodulo5.component.ColorRadioButtonsWithMargin
import com.example.springmodulo5.component.NumberRadioButtons
import com.example.springmodulo5.component.bottomAppBar
import com.example.springmodulo5.component.topBar
import com.example.springmodulo5.models.Cart
import com.example.springmodulo5.models.ColorItem
import com.example.springmodulo5.models.Product
import com.example.springmodulo5.viewmodels.CartViewModel


import com.example.springmodulo5.viewmodels.ProductsViewModel
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navController: NavController, viewModel: ProductsViewModel, id: Int, viewModelCart:CartViewModel)
{
    Scaffold(
        topBar = { topBar(viewModel.getProductId(id).name, navController ) },
        content = {Column {

            ContentDetailView(it, navController , viewModel , id , viewModelCart)
        }},
        bottomBar = { bottomAppBar(navController ) }
    )

}
@Composable
fun ContentDetailView(it: PaddingValues, navController: NavController, viewModel: ProductsViewModel,id:Int,viewModelCart : CartViewModel) {
        val context = LocalContext.current
        val scrollState = rememberScrollState()
        val myColors = listOf(ColorItem(Color.Black,"Black"),ColorItem(Color.DarkGray,"Gray"),ColorItem(Color.Red,"Red"),
        ColorItem(Color.Cyan,"Cyan"))
        val product: Product = viewModel.getProductId(id)
        var selectedColor by remember { mutableStateOf(myColors[0].color) }
        var nameColor by remember { mutableStateOf("")}
        var selectedNumber by remember { mutableStateOf(36) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier
                .padding(top = 60.dp)
                .shadow(2.dp))
            Image(
                painter = rememberImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Descripción", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.desc,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${ product.price.toInt()}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text ="Seleccione su color: ", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            ColorRadioButtonsWithMargin(colors = myColors, selectedColor = selectedColor) { color ->
                selectedColor = color.color
                nameColor = color.name
                Log.e("MyApp",nameColor)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = if(selectedNumber <37 ) "Seleccione su talla:" else "Talla: ", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            NumberRadioButtons { number ->
                selectedNumber = number
                // Do something with the selected number
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {viewModelCart.addToCart(product, selectedNumber.toString(),nameColor)
                navController.navigate("cart")},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)){
                Text("Agregar al carrito")
            }
        }
    }
