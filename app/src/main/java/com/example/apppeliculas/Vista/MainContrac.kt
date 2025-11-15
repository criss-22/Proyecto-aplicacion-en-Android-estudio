package com.example.apppeliculas.Vista

import com.example.apppeliculas.Modelo.bdpelis

interface MainContrac {
    fun mostrarPeliculas(peliculas: List<bdpelis>)
    fun mostrarError(mensaje: String)
}