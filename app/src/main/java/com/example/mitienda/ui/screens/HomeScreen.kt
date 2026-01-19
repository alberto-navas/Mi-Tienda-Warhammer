package com.example.mitienda.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mitienda.model.Producto
import com.example.mitienda.ui.components.ProductoCard
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun HomeScreen(
    productos: List<Producto>,
    onVerProducto: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        items(productos) { producto ->
            ProductoCard(producto = producto, onVer = onVerProducto)
        }
    }
}
