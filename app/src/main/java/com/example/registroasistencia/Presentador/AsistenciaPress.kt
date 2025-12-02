package com.example.registroasistencia.Presentador

import com.example.registroasistencia.Modelo.AsistenciaModel
import com.example.registroasistencia.Vista.Contratos.AsistenciaInt

class AsistenciaPress (private val view: AsistenciaInt){

    private val model = AsistenciaModel()

    fun cargarAsistencias(idEmpleado: Int) {
        model.obtenerAsistencia(idEmpleado) { mapa, error ->
            if (mapa != null) view.asistenciaSuccess(mapa)
            else view.asistenciaFailure(error ?: "Error desconocido")
        }
    }
}