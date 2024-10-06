package com.example.springmodulo5.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBar(title: String, navController: NavController){
    CenterAlignedTopAppBar(title = { Text(text = "$title",color = androidx.compose.ui.graphics.Color.White)
    },colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary
    ),navigationIcon = {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    })
}



@Composable
fun bottomAppBar(navController: NavController) {
        BottomAppBar(modifier = Modifier.fillMaxWidth(), containerColor = MaterialTheme.colorScheme.primary) {
            IconButton(onClick = {navController.navigate("inicio")}, modifier = Modifier.padding(25.dp)) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            Icon(Icons.Filled.Search, contentDescription = "Search", modifier = Modifier.padding(25.dp))
            IconButton(onClick ={navController.navigate("cart")}, modifier = Modifier.padding(25.dp)) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
            }
            Icon(Icons.Filled.Person, contentDescription = "User", modifier = Modifier.padding(25.dp))

        }
}