package com.example.registroasistencia.Vista.Contratos

import com.example.registroasistencia.Modelo.DataClass.LoginResponse

interface LoginInt {
    fun accederSuccess(datos: LoginResponse)
    fun accederFailure(error: String)
}