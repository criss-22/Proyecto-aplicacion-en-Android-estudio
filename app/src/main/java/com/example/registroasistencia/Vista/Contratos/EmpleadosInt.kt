package com.example.registroasistencia.Vista.Contratos

import com.example.registroasistencia.Modelo.Empleado

interface EmpleadosInt {

    fun mostrarListado(lista: List<Empleado>)
    fun mostrarBusqueda(lista: List<Empleado>)
    fun mostrarError(mensaje: String)
}