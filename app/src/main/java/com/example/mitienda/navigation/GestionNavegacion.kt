package com.example.mitienda.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.NavEntry
import com.example.mitienda.ui.screens.ConfirmacionScreen
import com.example.mitienda.ui.screens.DetalleScreen
import com.example.mitienda.ui.screens.HomeScreen
import com.example.mitienda.model.Catalogo

@Composable
fun GestionNavegacion() {

    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {

                is Routes.Home -> NavEntry(key) {
                    HomeScreen(
                        productos = Catalogo.productos,
                        onVerProducto = { id ->
                            backStack.add(Routes.Detalle(id))
                        }
                    )
                }

                is Routes.Detalle -> NavEntry(key) {
                    DetalleScreen(
                        producto = Catalogo.getById(key.id),
                        onComprar = { backStack.add(Routes.Confirmacion) },
                        onAtras = { backStack.removeLastOrNull() }
                    )
                }

                is Routes.Confirmacion -> NavEntry(key) {
                    ConfirmacionScreen(
                        onVolverHome = {
                            while (backStack.size > 1) backStack.removeLastOrNull()
                        }
                    )
                }

                else -> NavEntry(key) { }
            }
        }
    )
}
