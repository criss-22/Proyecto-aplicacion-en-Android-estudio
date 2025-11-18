package com.example.registroasistencia.Presentador

import com.example.registroasistencia.Modelo.LoginModel
import com.example.registroasistencia.Vista.Contratos.LoginInt

class LoginPress (private val view: LoginInt){
    private val model= LoginModel()
    fun acceder(email: String, password: String) {
        model.acceder(email, password) { datos, error ->
            if (datos != null && datos.success == true) {
                view.accederSuccess(datos)
            } else {
                view.accederFailure(datos?.mensaje ?: error ?: "Credenciales incorrectas")
        }
    }
}
    }
/*fun acceder(email: String, password: String) {

        // Validaciones básicas
        if (email.isBlank() || password.isBlank()) {
            view.accederFailure("Los campos no pueden estar vacíos")
            return
        }

        // lamada al Modelo
        model.acceder(email, password) { datos, error ->

            when {
                //Caso: respuesta correcta y login exitoso
                datos != null && datos.success == true -> {
                    view.accederSuccess(datos)
                }

                //Caso: respuesta correcta pero login rechazado por el servidor
                datos != null -> {
                    view.accederFailure(datos.mensaje)
                }

                // Caso: error de conexión o servidor caído
                error != null -> {
                    view.accederFailure(error)
                }

                // Caso extremo
                else -> {
                    view.accederFailure("Ocurrió un error desconocido")
                }
            }
        }
    }*/