package com.example.mitienda.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.mitienda.model.Producto

@Composable
fun ProductoCard(
    producto: Producto,
    onVer: (String) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Usamos AsyncImage de Coil para cargar la URL de internet
            AsyncImage(
                model = producto.imageUrl,
                contentDescription = producto.nombre,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = producto.nombre)
                Text(text = "${producto.precio} €")
            }

            Button(onClick = { onVer(producto.id) }) {
                Text("Ver")
            }
        }
    }
}
