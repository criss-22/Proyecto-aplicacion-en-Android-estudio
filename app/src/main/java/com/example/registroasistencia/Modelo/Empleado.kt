package com.example.registroasistencia.Modelo

data class Empleado(
    val Id_Empleado: String,
    val Nombre: String,
    val Apellido_Paterno: String,
    val Apellido_Materno: String,
    val Correo: String,
    val Telefono: String,
    val foto: String?,
    val Id_Departamento: String,
    val Id_Puesto: String,
    val Id_Tipo_Usuario: String
)
