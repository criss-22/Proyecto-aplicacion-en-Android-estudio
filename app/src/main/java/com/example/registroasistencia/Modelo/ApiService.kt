package com.example.registroasistencia.Modelo

import com.example.registroasistencia.Modelo.DataClass.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("correo") correo: String,
        @Field("contrasena") contrasena: String
    ): Call<LoginResponse>
    /*@GET("empresa/info.php")//api base de datos
    fun getInfoMisionVision(): Call<MisionVisionRespone>*/
}