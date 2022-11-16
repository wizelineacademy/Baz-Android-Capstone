package com.example.criptobitsoproyectwz.NavigationCompose

sealed class Rutas(var ruta: String) {
    object Home: Rutas("Home")
    object Detalle: Rutas("Detalles")

}