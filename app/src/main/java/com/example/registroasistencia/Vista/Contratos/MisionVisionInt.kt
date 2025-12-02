package com.example.registroasistencia.Vista.Contratos

import com.example.registroasistencia.Modelo.DataClass.MisionVisionRespone


interface MisionVisionInt {
    //fun mostrarCargando(mostrar: Boolean)

    fun mostrarInfo(datos: MisionVisionRespone)

    fun mostrarError(mensaje: String)
}