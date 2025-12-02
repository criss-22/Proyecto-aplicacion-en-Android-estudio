package com.example.registroasistencia.Vista.Contratos

interface AsistenciaInt {
    fun asistenciaSuccess(mapa: Map<String, String>)
    fun asistenciaFailure(error: String)
}