package com.example.registroasistencia.Modelo.DataClass

data class LoginResponse(val success: Boolean,
                         val mensaje: String,
                         val tipo_usuario: Int?,
                         val id: Int?)