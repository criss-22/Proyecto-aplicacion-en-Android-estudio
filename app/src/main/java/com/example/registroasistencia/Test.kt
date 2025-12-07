package com.example.registroasistencia
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import com.example.registroasistencia.Modelo.ApiDatos
import com.example.registroasistencia.Modelo.ApiEmpleados
import com.example.registroasistencia.Modelo.ApiMisionVision
import com.example.registroasistencia.Modelo.ApiService
import com.example.registroasistencia.Modelo.DataClass.Departamento
import com.example.registroasistencia.Modelo.DataClass.Empleado
import com.example.registroasistencia.Modelo.DataClass.LoginResponse
import com.example.registroasistencia.Modelo.DataClass.MisionVisionRespone
import com.example.registroasistencia.Modelo.DataClass.Puesto
import com.example.registroasistencia.Modelo.DataClass.RespuestaApi
import com.example.registroasistencia.Modelo.DataClass.TipoUsuario
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
object Test {
    fun String.toRequest(): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), this)
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://juandios.grupoctic.com/Peliculas/api/")  // tu misma URL del modelo
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiEmpleados::class.java)
    private val apiD=retrofit.create(ApiDatos::class.java)
    private val apiMisionVision=retrofit.create(ApiMisionVision::class.java)
    private val apiService=retrofit.create(ApiService::class.java)

    fun probarListar() {
        api.listarEmpleados().enqueue(object : Callback<List<Empleado>> {

            override fun onResponse(
                call: Call<List<Empleado>>,
                response: Response<List<Empleado>>
            ) {
                Log.d("PRUEBA_RETROFIT", "LISTAR => ${response.body()}")
            }

            override fun onFailure(call: Call<List<Empleado>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR => ${t.message}")
            }
        })
    }

    fun probarBuscar(termino: String) {
        api.buscarEmpleados(termino).enqueue(object : Callback<List<Empleado>> {

            override fun onResponse(
                call: Call<List<Empleado>>,
                response: Response<List<Empleado>>
            ) {
                Log.d("PRUEBA_RETROFIT", "BUSCAR => ${response.body()}")
            }

            override fun onFailure(call: Call<List<Empleado>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR BUSCAR => ${t.message}")
            }
        })
    }

    fun probarCrear(context: Context) {

        val nombre = "Pedro".toRequest()
        val apaterno = "López".toRequest()
        val amaterno = "Ramírez".toRequest()
        val correo = "test${System.currentTimeMillis()}@mail.com".toRequest()
        val telefono = "999".toRequest()
        val contrasena = "123".toRequest()
        val tipoUsuario = "2".toRequest()
        val departamento = "7".toRequest()
        val puesto = "3".toRequest()

        // =====================================================

        // =====================================================

        val drawable = context.getDrawable(R.drawable.imglogin)!!
        val bitmap = (drawable as BitmapDrawable).bitmap

        val tempFile = File(context.cacheDir, "imglogin.jpg")
        val output = FileOutputStream(tempFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output)
        output.close()

        val imageRequest = tempFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val fotoPart = MultipartBody.Part.createFormData(
            "foto",
            "imglogin.jpg",
            imageRequest
        )

        // =====================================================

        // =====================================================

        api.crearEmpleado(
            nombre, apaterno, amaterno, correo, telefono,
            contrasena, tipoUsuario, departamento, puesto,
            fotoPart
        ).enqueue(object : Callback<List<RespuestaApi>> {

            override fun onResponse(
                call: Call<List<RespuestaApi>>,
                response: Response<List<RespuestaApi>>
            ) {
                Log.d("PRUEBA_RETROFIT", "CREAR => ${response.body()}")
            }

            override fun onFailure(call: Call<List<RespuestaApi>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR => ${t.message}")
            }
        })
    }

    fun probarActualizar(context: Context, enviarImagen: Boolean) {

        val id = "19".toRequest()

        val nombre = "Carlos".toRequest()
        val apaterno = "Martínez".toRequest()
        val amaterno = "Gómez".toRequest()
        val correo = "actualizado@mail.com".toRequest()
        val telefono = "555123".toRequest()
        val contrasena = "123".toRequest()
        val tipoUsuario = "2".toRequest()
        val departamento = "7".toRequest()
        val puesto = "3".toRequest()

        var fotoPart: MultipartBody.Part? = null

        // =======================================================

        // =======================================================
        if (enviarImagen) {

            // 1. Obtener Drawable
            val drawable = context.getDrawable(R.drawable.imglogin)!!

            // 2. Convertir a Bitmap
            val bitmap = (drawable as BitmapDrawable).bitmap

            // 3. Crear archivo temporal
            val tempFile = File(context.cacheDir, "imglogin.png")
            val outputStream = FileOutputStream(tempFile)

            // 4. Guardar bitmap como PNG
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // 5. Convertir archivo a RequestBody
            val imgReq = tempFile.asRequestBody("image/png".toMediaTypeOrNull())

            // 6. Crear parte Multipart
            fotoPart = MultipartBody.Part.createFormData(
                "foto",
                "imglogin.png",
                imgReq
            )
        }

        // =======================================================

        // =======================================================
        api.actualizarEmpleado(
            id,
            nombre,
            apaterno,
            amaterno,
            correo,
            telefono,
            contrasena,
            tipoUsuario,
            departamento,
            puesto,
            fotoPart
        ).enqueue(object : Callback<List<RespuestaApi>> {

            override fun onResponse(
                call: Call<List<RespuestaApi>>,
                response: Response<List<RespuestaApi>>
            ) {
                Log.d("PRUEBA_RETROFIT", "ACTUALIZAR => ${response.body()}")
            }

            override fun onFailure(call: Call<List<RespuestaApi>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR ACTUALIZAR => ${t.message}")
            }
        })
    }


    fun probarEliminar() {

        val id = 19 // ID a eliminar

        api.eliminarEmpleado(id)
            .enqueue(object : Callback<List<RespuestaApi>> {

                override fun onResponse(
                    call: Call<List<RespuestaApi>>,
                    response: Response<List<RespuestaApi>>
                ) {
                    Log.d("PRUEBA_RETROFIT", "ELIMINAR => ${response.body()}")
                }

                override fun onFailure(call: Call<List<RespuestaApi>>, t: Throwable) {
                    Log.e("PRUEBA_RETROFIT", "ERROR ELIMINAR => ${t.message}")
                }
            })
    }


    fun probarListarDepartamentos() {
        apiD.listarDepartamentos()
            .enqueue(object : Callback<List<Departamento>> {
                override fun onResponse(
                    call: Call<List<Departamento>>,
                    response: Response<List<Departamento>>
                ) {
                    Log.d("PRUEBA_RETROFIT", "Departamentos => ${response.body()}")
                }

                override fun onFailure(call: Call<List<Departamento>>, t: Throwable) {
                    Log.e("PRUEBA_RETROFIT", "ERROR Departamentos => ${t.message}")
                }
            })
    }

    fun probarListarPuestos() {
        apiD.listarPuestos()
            .enqueue(object : Callback<List<Puesto>> {
                override fun onResponse(
                    call: Call<List<Puesto>>,
                    response: Response<List<Puesto>>
                ) {
                    Log.d("PRUEBA_RETROFIT", "Puestos => ${response.body()}")
                }

                override fun onFailure(call: Call<List<Puesto>>, t: Throwable) {
                    Log.e("PRUEBA_RETROFIT", "ERROR Puestos => ${t.message}")
                }
            })
    }

    fun probarListarTiposUsuario() {
        apiD.listarTiposUsuario()
            .enqueue(object : Callback<List<TipoUsuario>> {
                override fun onResponse(
                    call: Call<List<TipoUsuario>>,
                    response: Response<List<TipoUsuario>>
                ) {
                    Log.d("PRUEBA_RETROFIT", "TiposUsuario => ${response.body()}")
                }

                override fun onFailure(call: Call<List<TipoUsuario>>, t: Throwable) {
                    Log.e("PRUEBA_RETROFIT", "ERROR TiposUsuario => ${t.message}")
                }
            })
    }


    fun probarMisionVision() {
        apiMisionVision.misionVision()
            .enqueue(object : Callback<List<MisionVisionRespone>> {
                override fun onResponse(
                    call: Call<List<MisionVisionRespone>>,
                    response: Response<List<MisionVisionRespone>>
                ) {
                    if (response.isSuccessful) {
                        val lista = response.body()
                        Log.d("PRUEBA_RETROFIT", "MisionVision => $lista")
                    } else {
                        Log.e("PRUEBA_RETROFIT", "Error HTTP => ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<MisionVisionRespone>>, t: Throwable) {
                    Log.e("PRUEBA_RETROFIT", "ERROR MisionVision => ${t.message}")
                }
            })
    }

    fun probarLogin(correo: String, contrasena: String) {
        apiService.login(correo, contrasena)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResp = response.body()
                        Log.d("PRUEBA_RETROFIT", "LOGIN => $loginResp")
                    } else {
                        Log.e("PRUEBA_RETROFIT", "Error HTTP => ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("PRUEBA_RETROFIT", "ERROR LOGIN => ${t.message}")
                }
            })
    }



}