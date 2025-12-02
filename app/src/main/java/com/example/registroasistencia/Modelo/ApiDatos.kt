package com.example.registroasistencia.Modelo
import com.example.registroasistencia.Modelo.DataClass.Departamento
import com.example.registroasistencia.Modelo.DataClass.Puesto
import com.example.registroasistencia.Modelo.DataClass.TipoUsuario
import retrofit2.Call
import retrofit2.http.GET

interface ApiDatos {
    @GET("departamentos.php")
    fun listarDepartamentos(): Call<List<Departamento>>

    @GET("puestos.php")
    fun listarPuestos(): Call<List<Puesto>>

    @GET("tipos_usuario.php")
    fun listarTiposUsuario(): Call<List<TipoUsuario>>
}