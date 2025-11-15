package com.example.apppeliculas.Presentador

import com.example.apppeliculas.Modelo.RegistroModel
import com.example.apppeliculas.Vista.RegistroContrac

class RegistrPresenter (
private val vista: RegistroContrac,
private val model: RegistroModel
) {
    fun registrarUsuario(nombreUsuario: String, email: String, password: String) {
        model.registrarUsuario(nombreUsuario, email, password, object : RegistroModel.OnRegistroListener {
            override fun onSuccess(message: String) {
                vista.mostrarMensaje(message)
                vista.registroExitoso()
            }

            override fun onFailure(message: String) {
                vista.mostrarMensaje(message)
            }
        })
    }
}