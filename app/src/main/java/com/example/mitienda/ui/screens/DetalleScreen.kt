package com.example.mitienda.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mitienda.model.Producto

@Composable
fun DetalleScreen(
    producto: Producto,
    onComprar: () -> Unit,
    onAtras: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(producto.imageRes),
            contentDescription = producto.nombre,
            modifier = Modifier
                .size(220.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = producto.nombre)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "${producto.precio} €")
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = producto.descripcion)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onComprar) {
            Text("Compra ahora")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onAtras) {
            Text("Atrás")
        }
    }
}
