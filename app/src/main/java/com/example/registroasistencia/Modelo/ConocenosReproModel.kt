package com.example.registroasistencia.Modelo

import com.example.registroasistencia.Modelo.DataClass.MisionVisionRespone
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ConocenosReproModel {
    ///Nota si funciona

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://juandios.grupoctic.com/Peliculas/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiMisionVision::class.java)

    private val URL = "https://juandios.grupoctic.com/Peliculas/"

    fun obtenerURL(callback: (String?, String?) -> Unit) {

        api.misionVision().enqueue(object : Callback<List<MisionVisionRespone>> {

            override fun onResponse(
                call: Call<List<MisionVisionRespone>>,
                response: Response<List<MisionVisionRespone>>
            ) {
                // Si la API respondió correctamente
                if (response.isSuccessful) {
                    val lista = response.body()

                    // Si hay datos
                    if (lista != null && lista.isNotEmpty()) {

                        val nombreVideo = lista[0].nombreVideo

                        // Si la BD trae un nombre válido
                        if (!nombreVideo.isNullOrBlank()) {
                            val urlCompleta = URL + nombreVideo.trim()
                            callback(urlCompleta, null)
                        } else {
                            callback(null, "El nombre del video está vacío")
                        }

                    }
                } else {
                    callback(null, "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<MisionVisionRespone>>, t: Throwable) {
                callback(null, "Error de conexión: ${t.message}")
            }
        })
    }
}
