package com.example.apppeliculas.Modelo

class ReproducirModel {
    fun obtenerUrlVideo(nombrePelicula: String): String {
        // Aquí podrías tener lógica más compleja (por ejemplo, leer de una base de datos o API)
        return "https://cristopher.grupoctic.com/Peliculas/peli/$nombrePelicula"
    }
}