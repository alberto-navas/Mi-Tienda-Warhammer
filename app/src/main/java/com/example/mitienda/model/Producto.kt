package com.example.mitienda.model

import com.google.firebase.firestore.DocumentId

/**
 * Modelo de datos para los productos de la tienda.
 * Incluye valores por defecto para el constructor vacío que requiere Firestore.
 */
data class Producto(
    @DocumentId
    var id: String = "",
    var nombre: String = "",
    var precio: Double = 0.0,
    var descripcion: String = "",
    var imageUrl: String = ""
)
