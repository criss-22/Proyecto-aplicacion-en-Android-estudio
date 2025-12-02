package com.example.registroasistencia.Modelo

import com.example.registroasistencia.Modelo.DataClass.Departamento
import com.example.registroasistencia.Modelo.DataClass.Puesto
import com.example.registroasistencia.Modelo.DataClass.RespuestaApi
import com.example.registroasistencia.Modelo.DataClass.TipoUsuario
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FormularioMod {
    private val retrofit= Retrofit.Builder()
        .baseUrl("https://juandios.grupoctic.com/Peliculas/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()

    private val apiService = retrofit.create(ApiDatos::class.java)
    private val api=retrofit.create(ApiEmpleados::class.java)

    fun listarDepartamentos(callback: (List<Departamento>?,String?)-> Unit) {

        val call = apiService.listarDepartamentos()

        call.enqueue(object : Callback<List<Departamento>> {
            override fun onResponse(
                call: Call<List<Departamento>>,
                response: Response<List<Departamento>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(it, null)
                    } ?: callback(null, "Respuesta vacía")
                }else {
                    callback(null, "Error ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<Departamento>?>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }

        })
    }


    fun listarPuestos(callback: (List<Puesto>?, String?)-> Unit) {

        val call = apiService.listarPuestos()

        call.enqueue(object : Callback<List<Puesto>> {
            override fun onResponse(
                call: Call<List<Puesto>>,
                response: Response<List<Puesto>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(it, null)
                    } ?: callback(null, "Respuesta vacía")
                }else {
                    callback(null, "Error ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<Puesto>?>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }

        })
    }


    fun listarTiposUsuario(callback: (List<TipoUsuario>?, String?)-> Unit) {

        val call = apiService.listarTiposUsuario()

        call.enqueue(object : Callback<List<TipoUsuario>> {
            override fun onResponse(
                call: Call<List<TipoUsuario>>,
                response: Response<List<TipoUsuario>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(it, null)
                    } ?: callback(null, "Respuesta vacía")
                }else {
                    callback(null, "Error ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<TipoUsuario>?>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }

        })
    }



    fun actualizarEmpleado(
        id: RequestBody,
        nombre: RequestBody,
        apaterno: RequestBody,
        amaterno: RequestBody,
        correo: RequestBody,
        telefono: RequestBody,
        contrasena: RequestBody,
        tipoUsuario: RequestBody,
        departamento: RequestBody,
        puesto: RequestBody,
        foto: MultipartBody.Part?,
        callback: (Boolean, String) -> Unit
    ) {
        api.actualizarEmpleado(
            id, nombre, apaterno, amaterno, correo,
            telefono, contrasena, tipoUsuario, departamento, puesto, foto
        ).enqueue(object : Callback<List<RespuestaApi>> {
            override fun onResponse(
                call: Call<List<RespuestaApi>>,
                response: Response<List<RespuestaApi>>
            ) {
                val body = response.body()?.firstOrNull()
                if (body?.success == true) {
                    callback(true, body.message)
                } else {
                    callback(false, body?.message ?: "Error desconocido")
                }
            }

            override fun onFailure(call: Call<List<RespuestaApi>>, t: Throwable) {
                callback(false, t.message ?: "Error de conexión")
            }
        })
    }




    fun crearEmpleado(
        nombre: RequestBody,
        apaterno: RequestBody,
        amaterno: RequestBody,
        correo: RequestBody,
        telefono: RequestBody,
        contrasena: RequestBody,
        tipoUsuario: RequestBody,
        departamento: RequestBody,
        puesto: RequestBody,
        foto: MultipartBody.Part?,
        callback: (Boolean, String) -> Unit
    ) {
        api.crearEmpleado(
            nombre, apaterno, amaterno, correo, telefono,
            contrasena, tipoUsuario, departamento, puesto, foto
        ).enqueue(object : Callback<List<RespuestaApi>> {
            override fun onResponse(
                call: Call<List<RespuestaApi>>,
                response: Response<List<RespuestaApi>>
            ) {
                val body = response.body()?.firstOrNull()
                if (body?.success == true) {
                    callback(true, body.message)
                } else {
                    callback(false, body?.message ?: "Error desconocido")
                }
            }

            override fun onFailure(call: Call<List<RespuestaApi>>, t: Throwable) {
                callback(false, t.message ?: "Error de conexión")
            }
        })
    }


    fun eliminarEmpleado(
        idEmpleado: Int,
        callback: (Boolean, String) -> Unit
    ) {
        api.eliminarEmpleado(idEmpleado).enqueue(object : retrofit2.Callback<List<RespuestaApi>> {
            override fun onResponse(
                call: retrofit2.Call<List<RespuestaApi>>,
                response: retrofit2.Response<List<RespuestaApi>>
            ) {
                val body = response.body()?.firstOrNull()
                if (body?.success == true) {
                    callback(true, body.message)
                } else {
                    callback(false, body?.message ?: "Error desconocido")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<RespuestaApi>>, t: Throwable) {
                callback(false, t.message ?: "Error de conexión")
            }
        })
    }





}