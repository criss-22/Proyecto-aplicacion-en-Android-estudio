package com.example.registroasistencia.Modelo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiAsistencia {
    @GET("apiAsistencia.php")
    fun obtenerAsistencia(
        @Query("idEmpleado") idEmpleado: Int
    ): Call<Map<String, String>>
}