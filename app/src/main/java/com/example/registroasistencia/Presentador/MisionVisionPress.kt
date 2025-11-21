package com.example.registroasistencia.Presentador

import com.example.registroasistencia.Modelo.MisionVisionModel
import com.example.registroasistencia.Vista.Contratos.MisionVisionInt

class MisionVisionPress(private val view: MisionVisionInt) {
    private val model = MisionVisionModel()

    fun cargarInfo()
    {


        model.obtenerInfo { lista, error ->
            if (lista != null && lista.isNotEmpty()) {
                view.mostrarInfo(lista[0])
            } else {
                view.mostrarError(error ?: "No hay datos disponibles")
            }
    }
    }
}