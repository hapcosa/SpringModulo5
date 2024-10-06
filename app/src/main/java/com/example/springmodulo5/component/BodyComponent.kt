package com.example.springmodulo5.component

import android.media.ThumbnailUtils
import android.util.Log
import android.widget.GridView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.springmodulo5.R
import com.example.springmodulo5.models.Product
import com.example.springmodulo5.viewmodels.ProductsViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.springmodulo5.models.CartProduct
import com.example.springmodulo5.models.CartProductRef
import com.example.springmodulo5.models.ColorItem
import com.example.springmodulo5.viewmodels.CartViewModel

@Composable
fun productCard(product: Product, navController: NavController){
    Card(modifier = Modifier
        .width(150.dp)
        .height(240.dp)
        .padding(8.dp)
        .clickable { navController.navigate("detail/${product.productId}") }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${product.price.toInt()}",
                    style = MaterialTheme.typography.titleSmall
                )

            }
        }
    }

}


@Composable
fun ShoppingCartProductItem(product: Product, navController: NavController, productRef: CartProductRef,viewModel: CartViewModel) {
   Log.i("MyApp", productRef.color)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("detail/${product.productId}") },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = rememberImagePainter(product.imageUrl),
            contentDescription = product.name,
            modifier = Modifier
                .size(80.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row() {
                Text(
                    text = "Color: ",
                    style = MaterialTheme.typography.bodySmall
                )
                Log.e("MyApp", "${productRef.color}")
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            when (productRef.color) {
                                "Red" -> Color.Red
                                "Black" -> Color.Black
                                "Cyan" -> Color.Cyan
                                "Gray" -> Color.DarkGray
                                else -> Color.Gray
                            }, CircleShape
                        )
                        .align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(){
                Text(
                    text = "Talla: ",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "${productRef.talla} ",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier=Modifier.weight(1f).fillMaxHeight())
        IconButton(onClick = { viewModel.deleteCartProduct(productRef) },
            modifier=Modifier.padding(5.dp),){
            Icon(Icons.Filled.Delete, contentDescription = "delete",Modifier.shadow(1.dp))
        }
    }
}

@Composable
fun ColorRadioButtonsWithMargin(
    colors: List<ColorItem>,
    selectedColor: Color,
    onColorSelected: (ColorItem) -> Unit
) {
    val margin = 8.dp // Margin size
    val circleSize = 26.dp // Circle size

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(circleSize + (25.dp)) // Total size including margin
                    .clickable { onColorSelected(color) }
                    .padding(margin) // Add margin
                    .background(
                        if (color.color == selectedColor) color.color else Color.Transparent, // Fill margin when selected
                        CircleShape
                    )
                    .border(
                        width = if (color.color == selectedColor) 0.dp else 1.dp, // Hide border when selected
                        color = color.color,
                        shape = CircleShape
                    )
            ) {
                // Inner circle with the color
                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .background(color.color, CircleShape)
                        .align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun NumberRadioButtons(
    numbers: List<Int> = listOf(37, 38, 39, 40,41, 42),
    onNumberSelected: (Int) -> Unit
) {
    var selectedNumber by remember { mutableStateOf(numbers[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        numbers.forEach { number ->
            Box(
                modifier = Modifier
                    .size(36.dp).padding(4.dp)
                    .background(
                        if (number == selectedNumber) MaterialTheme.colorScheme.primary else Color.LightGray,
                        CircleShape
                    )
                    .border(
                        width = if (number == selectedNumber) 2.dp else 0.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .clickable {
                        selectedNumber = number
                        onNumberSelected(number)
                    }
                    .wrapContentSize(Alignment.Center),
            ) {
                Text(
                    text = "$number",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                    color = if(number == selectedNumber) Color.White else Color.Black
                )
            }
        }
    }
}