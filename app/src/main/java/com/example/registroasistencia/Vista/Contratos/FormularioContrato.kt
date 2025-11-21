package com.example.registroasistencia.Vista.Contratos

import com.example.registroasistencia.Modelo.DataClass.Departamento
import com.example.registroasistencia.Modelo.DataClass.Puesto
import com.example.registroasistencia.Modelo.DataClass.TipoUsuario

interface FormularioContrato {
    fun mostrarDepartamentos(lista: List<Departamento>)
    fun mostrarPuestos(lista: List<Puesto>)
    fun mostrarTiposUsuario(lista: List<TipoUsuario>)
    fun mostrarError(mensaje: String)
    fun mostrarMensajeExito(msg: String)

}