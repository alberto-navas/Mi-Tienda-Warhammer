package com.example.mitienda.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.NavEntry
import com.example.mitienda.ui.screens.*
import com.example.mitienda.viewmodel.AuthViewModel
import com.example.mitienda.viewmodel.TiendaViewModel

@Composable
fun GestionNavegacion() {
    // Instancia de los ViewModels siguiendo la arquitectura MVVM
    val authViewModel: AuthViewModel = viewModel()
    val tiendaViewModel: TiendaViewModel = viewModel()
    
    // Si ya hay un usuario logueado, empezamos en Home, si no en Login
    val startRoute = if (authViewModel.userEmail.value != null) Routes.Home else Routes.Login
    val backStack = rememberNavBackStack(startRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Routes.Login -> NavEntry(key) {
                    LoginScreen(
                        authViewModel = authViewModel,
                        onLoginSuccess = { backStack.add(Routes.Home) },
                        onNavigateToRegister = { backStack.add(Routes.Registro) }
                    )
                }

                is Routes.Registro -> NavEntry(key) {
                    RegisterScreen(
                        authViewModel = authViewModel,
                        onRegisterSuccess = { backStack.removeLastOrNull() },
                        onCancel = { backStack.removeLastOrNull() }
                    )
                }

                is Routes.Home -> NavEntry(key) {
                    HomeScreen(
                        userEmail = authViewModel.userEmail.value ?: "Usuario",
                        onLogout = {
                            authViewModel.signOut {
                                while (backStack.isNotEmpty()) backStack.removeLastOrNull()
                                backStack.add(Routes.Login)
                            }
                        },
                        tiendaViewModel = tiendaViewModel,
                        onVerProducto = { id -> backStack.add(Routes.Detalle(id)) },
                        onEditarProducto = { id -> backStack.add(Routes.Edit(id)) }
                    )
                }

                is Routes.Detalle -> NavEntry(key) {
                    val producto = tiendaViewModel.productos.value.find { it.id == key.id }
                    if (producto != null) {
                        DetalleScreen(producto = producto, onNavigateBack = { backStack.removeLastOrNull() })
                    } else {
                        backStack.removeLastOrNull()
                    }
                }

                is Routes.Edit -> NavEntry(key) {
                    val producto = tiendaViewModel.productos.value.find { it.id == key.id }
                    if (producto != null) {
                        EditScreen(
                            producto = producto,
                            tiendaViewModel = tiendaViewModel,
                            onNavigateBack = { backStack.removeLastOrNull() }
                        )
                    } else {
                        backStack.removeLastOrNull()
                    }
                }

                is Routes.Confirmacion -> NavEntry(key) {
                    ConfirmacionScreen(onVolverHome = {
                        while (backStack.size > 1 && backStack.last() !is Routes.Home) {
                            backStack.removeLastOrNull()
                        }
                    })
                }

                else -> NavEntry(key) { }
            }
        }
    )
}
