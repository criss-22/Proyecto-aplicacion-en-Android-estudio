package com.example.registroasistencia.Modelo

import dalvik.system.ZipPathValidator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MisionVisionModel {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://juandios.grupoctic.com/Peliculas/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiMisionVision::class.java)

    fun obtenerInfo(callback: (List<MisionVisionRespone>?, String?) -> Unit) {


            api.misionVision().enqueue(object : Callback<List<MisionVisionRespone>> {

                override fun onResponse(
                    call: Call<List<MisionVisionRespone>>,
                    response: Response<List<MisionVisionRespone>>
                ) {
                    if (response.isSuccessful) {
                        callback(response.body(), null)
                    } else {
                        callback(null, "Error ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<MisionVisionRespone>>, t: Throwable) {
                    callback(null, t.message)
                }
            })
        }

}