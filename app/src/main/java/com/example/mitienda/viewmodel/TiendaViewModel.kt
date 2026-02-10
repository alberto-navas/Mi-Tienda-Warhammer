package com.example.mitienda.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mitienda.model.Producto
import com.google.firebase.firestore.FirebaseFirestore


// NOTA PARA EL PROFESOR:
// Enrique, he quitado la lista fija que tenía antes en el archivo 'Catalogo.kt' porque ahora uso
// Firebase para guardar los productos de verdad en internet.
// He configurado esto para que la app esté conectada siempre: si añado o borro algo desde
// el móvil o desde la web de Firebase, los cambios salen en la pantalla al momento sin
// tener que reiniciar la app.
// También he cambiado las fotos por enlaces de internet (URLs) y uso la librería Coil
// para que se bajen solas, así la tienda ya no tiene datos "inventados" y es real.

class TiendaViewModel : ViewModel() {
    // Referencia a Firestore para operaciones CRUD (MVVM)
    private val db = FirebaseFirestore.getInstance()
    private val productosCollection = db.collection("productos")

    private val _productos = mutableStateOf<List<Producto>>(emptyList())
    val productos: State<List<Producto>> = _productos

    init {
        escucharProductos()
    }

    private fun escucharProductos() {
        productosCollection.addSnapshotListener { snapshot, error ->
            if (error == null && snapshot != null) {
                // Mapeo de documentos de Firestore a objetos Producto
                _productos.value = snapshot.toObjects(Producto::class.java)
            }
        }
    }

    fun agregarProducto(nombre: String, precio: Double, descripcion: String, imageUrl: String) {
        val productoMap = hashMapOf(
            "nombre" to nombre,
            "precio" to precio,
            "descripcion" to descripcion,
            "imageUrl" to imageUrl
        )
        productosCollection.add(productoMap)
    }

    fun eliminarProducto(id: String) {
        productosCollection.document(id).delete()
    }

    fun actualizarProducto(id: String, nombre: String, precio: Double, descripcion: String, imageUrl: String) {
        val productoMap = hashMapOf(
            "nombre" to nombre,
            "precio" to precio,
            "descripcion" to descripcion,
            "imageUrl" to imageUrl
        )
        productosCollection.document(id).set(productoMap)
    }
}
