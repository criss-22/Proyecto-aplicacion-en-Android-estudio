package com.example.registroasistencia.Modelo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AsistenciaModel {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://juandios.grupoctic.com/Peliculas/api/") // MISMA URL DEL LOGIN
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiAsistencia::class.java)

    fun obtenerAsistencia(idEmpleado: Int, callback: (Map<String, String>?, String?) -> Unit) {

        val call = api.obtenerAsistencia(idEmpleado)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Error HTTP: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                callback(null, "Error de conexión: ${t.message}")
            }
        })
    }
}