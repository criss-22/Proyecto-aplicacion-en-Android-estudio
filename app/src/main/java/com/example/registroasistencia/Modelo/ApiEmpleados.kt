package com.example.registroasistencia.Modelo

import com.example.registroasistencia.Modelo.DataClass.Empleado
import com.example.registroasistencia.Modelo.DataClass.RespuestaApi
import retrofit2.http.GET
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEmpleados {
    // ===== LISTAR =====
    @GET("listar.php")
    fun listarEmpleados(): Call<List<Empleado>>

    // ===== BUSCAR =====
    @GET("buscar.php")
    fun buscarEmpleados(
        @Query("termino") termino: String
    ): Call<List<Empleado>>

    // ===== CREAR =====
    @Multipart
    @POST("crear.php")
    fun crearEmpleado(
        @Part("nombre") nombre: RequestBody,
        @Part("apaterno") apaterno: RequestBody,
        @Part("amaterno") amaterno: RequestBody,
        @Part("correo") correo: RequestBody,
        @Part("telefono") telefono: RequestBody,
        @Part("contrasena") contrasena: RequestBody,
        @Part("tipo_usuario") tipoUsuario: RequestBody,
        @Part("departamento") departamento: RequestBody,
        @Part("puesto") puesto: RequestBody,
        @Part foto: MultipartBody.Part? = null
    ): Call<List<RespuestaApi>>

    // ===== ACTUALIZAR =====
    @Multipart
    @POST("actualizar.php")
    fun actualizarEmpleado(
        @Part("id") id: RequestBody,
        @Part("nombre") nombre: RequestBody,
        @Part("apaterno") apaterno: RequestBody,
        @Part("amaterno") amaterno: RequestBody,
        @Part("correo") correo: RequestBody,
        @Part("telefono") telefono: RequestBody,
        @Part("contrasena") contrasena: RequestBody,
        @Part("tipo_usuario") tipoUsuario: RequestBody,
        @Part("departamento") departamento: RequestBody,
        @Part("puesto") puesto: RequestBody,
        @Part foto: MultipartBody.Part? = null
    ): Call<List<RespuestaApi>>

    // ===== ELIMINAR =====
    @FormUrlEncoded
    @POST("eliminar.php")
    fun eliminarEmpleado(
        @Field("id") idEmpleado: Int
    ): Call<List<RespuestaApi>>

}