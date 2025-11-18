package com.example.registroasistencia.Modelo

import retrofit2.Call
import retrofit2.http.GET

interface ApiMisionVision {
    @GET("MisionVision.php")
    fun misionVision(): Call<List<MisionVisionRespone>>
}