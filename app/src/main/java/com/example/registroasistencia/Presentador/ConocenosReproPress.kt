package com.example.registroasistencia.Presentador

import com.example.registroasistencia.Modelo.ConocenosReproModel
import com.example.registroasistencia.Vista.Contratos.ConocenoReproInt

class ConocenosReproPress(val vista: ConocenoReproInt) {

    private val modelo = ConocenosReproModel()

    fun cargarVideo()
    { modelo.obtenerURL { url, error ->
        if (url != null) {
            vista.mostrarVideo(url)
        } else {
            vista.mostrarError(error ?: "Error desconocido")
        }
    }
    }
}