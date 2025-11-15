package com.example.apppeliculas.Presentador


import com.example.apppeliculas.Modelo.LoginModel
import com.example.apppeliculas.Vista.LoginContrac
import com.example.apppeliculas.Vista.clsDatosRespuesta

class LoginPresenter(private val vista: LoginContrac) {
    private val model = LoginModel()

    fun iniciarSesion(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            vista.mostrarMensaje("Debe llenar todos los campos")
            return
        }

        model.iniciarSesion(email, password) { datos, error ->
            if (error != null) {
                vista.mostrarMensaje(error)
            } else if (datos != null && datos.firstOrNull()?.Estado == "Correcto") {
                vista.navegarAMain()
            } else {
                vista.mostrarMensaje("Usuario o contraseña incorrectos")
            }
        }
    }
}