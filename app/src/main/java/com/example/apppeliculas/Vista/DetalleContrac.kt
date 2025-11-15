package com.example.apppeliculas.Vista


import com.example.apppeliculas.Modelo.DetalleModel

interface DetalleContrac {
    fun mostrarPelicula(pelicula: DetalleModel)
    fun reproducirVideo(videoUrl: String)
}