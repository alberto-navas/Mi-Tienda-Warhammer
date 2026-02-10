package com.example.mitienda.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mitienda.model.Producto
import com.example.mitienda.viewmodel.TiendaViewModel

@Composable
fun HomeScreen(
    userEmail: String,
    onLogout: () -> Unit,
    tiendaViewModel: TiendaViewModel,
    onVerProducto: (String) -> Unit,
    onEditarProducto: (String) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var urlImagen by remember { mutableStateOf("") }
    val productos by tiendaViewModel.productos

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Cabecera: Bienvenida + Logout
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Bienvenido $userEmail", style = MaterialTheme.typography.bodyLarge)
            IconButton(onClick = onLogout) {
                Icon(Icons.Default.Logout, contentDescription = "Cerrar sesión")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Formulario para añadir (MVVM)
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre del producto") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = urlImagen, onValueChange = { urlImagen = it }, label = { Text("URL imagen") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (nombre.isNotBlank() && precio.isNotBlank()) {
                    tiendaViewModel.agregarProducto(nombre, precio.toDoubleOrNull() ?: 0.0, descripcion, urlImagen)
                    nombre = ""; precio = ""; descripcion = ""; urlImagen = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Producto")
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        // Lista CRUD
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(text = "${producto.precio}", style = MaterialTheme.typography.bodySmall)
                        }
                        Row {
                            IconButton(onClick = { onVerProducto(producto.id) }) {
                                Icon(Icons.Default.Search, contentDescription = "Ver")
                            }
                            IconButton(onClick = { onEditarProducto(producto.id) }) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar")
                            }
                            IconButton(onClick = { tiendaViewModel.eliminarProducto(producto.id) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}
