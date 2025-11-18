package com.example.registroasistencia.Modelo

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class LoginModel {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://juandios.grupoctic.com/Peliculas/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    // Función que realiza la solicitud al servidor
    fun acceder(correo: String, contrasena: String, callback: (LoginResponse?, String?) -> Unit) {
        val call = apiService.login(correo, contrasena)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) callback(body, null)
                    else callback(null, "Respuesta vacía del servidor")
                } else {
                    callback(null, "Error HTTP: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null, "Error de conexión: ${t.message}")
            }
        })
    }
}