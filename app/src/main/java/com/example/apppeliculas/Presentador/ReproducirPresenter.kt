package com.example.apppeliculas.Presentador

import com.example.apppeliculas.Vista.ReproducirContrac
import com.example.apppeliculas.Modelo.ReproducirModel


class ReproducirPresenter(val vista: ReproducirContrac) {
    private val modelo = ReproducirModel()

    fun cargarVideo(nombrePelicula: String) {
        if (nombrePelicula.isEmpty()) {
            vista.mostrarError("Nombre de película vacío")
        } else {
            val url = modelo.obtenerUrlVideo(nombrePelicula)
            vista.mostrarVideo(url)
        }
    }
}