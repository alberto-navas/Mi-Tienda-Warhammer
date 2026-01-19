package com.example.mitienda.model

import com.example.mitienda.R

object Catalogo {
    val productos = listOf(
        Producto(
            id = "1",
            nombre = "Caja de inicio",
            precio = 120.0,
            descripcion = "Perfecta para empezar. Incluye miniaturas y reglas básicas.",
            imageRes = R.drawable.cajadeinicio
        ),
        Producto(
            id = "2",
            nombre = "Kill Team",
            precio = 50.0,
            descripcion = "Escaramuzas rápidas con escuadras. Ideal para partidas cortas.",
            imageRes = R.drawable.killteam
        ),
        Producto(
            id = "3",
            nombre = "Death Korps of Krieg",
            precio = 65.0,
            descripcion = "Regimiento clásico. Miniaturas con estética brutal.",
            imageRes = R.drawable.krieg
        ),
        Producto(
            id = "4",
            nombre = "Libro de rol",
            precio = 45.0,
            descripcion = "Para campañas narrativas ambientadas en Warhammer.",
            imageRes = R.drawable.rolplay
        )
    )

    fun getById(id: String): Producto = productos.first { it.id == id }
}
