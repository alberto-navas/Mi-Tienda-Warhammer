package com.example.mitienda.model

object Catalogo {
    val productos = listOf(
        Producto(
            id = "1",
            nombre = "Caja de inicio",
            precio = 175.0,
            descripcion = "Perfecta para empezar. Incluye miniaturas y reglas básicas.",
            imageUrl = "https://www.warhammer.com/app/resources/catalog/product/920x950/_SPAWH40KUltimateStarterCombatPatrolVersionLEAD.jpg?fm=webp&w=892&h=920"
        ),
        Producto(
            id = "2",
            nombre = "Kill Team",
            precio = 55.0,
            descripcion = "Escaramuzas rápidas con escuadras. Ideal para partidas cortas.",
            imageUrl = "https://www.warhammer.com/app/resources/catalog/product/920x950/99120113102_KTXV26StealthBattlesuits01.jpg?fm=webp&w=892&h=920"
        ),
        Producto(
            id = "3",
            nombre = "Death Korps of Krieg",
            precio = 45.0,
            descripcion = "Regimiento clásico. Miniaturas con estética brutal.",
            imageUrl = "https://www.warhammer.com/app/resources/catalog/product/920x950/99120105135_AstraMilitarumDeathKorpsKriegInfantrySquad1.jpg?fm=webp&w=892&h=920"
        ),
        Producto(
            id = "4",
            nombre = "Libro de rol",
            precio = 29.99,
            descripcion = "Para campañas narrativas ambientadas en Warhammer.",
            imageUrl = "https://www.warhammer.com/app/resources/catalog/product/920x950/60700289025_ElectorCountsCardGame01a.jpg?fm=webp&w=892&h=920"
        )
    )

    fun getById(id: String): Producto = productos.first { it.id == id }
}
