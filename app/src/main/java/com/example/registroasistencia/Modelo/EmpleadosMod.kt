package com.example.registroasistencia.Modelo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class EmpleadosMod {
    private val retrofit= Retrofit.Builder()
        .baseUrl("https://juandios.grupoctic.com/Peliculas/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()

    private val api = retrofit.create(ApiEmpleados::class.java)

    fun listarEmpleados(callback: (List<Empleado>?,String?)-> Unit) {

        val call = api.listarEmpleados()

        call.enqueue(object : Callback<List<Empleado>> {
            override fun onResponse(
                call: Call<List<Empleado>>,
                response: Response<List<Empleado>>
            ) {
                if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it, null)
                        } ?: callback(null, "Respuesta vacía")
                    }else {
                    callback(null, "Error ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<Empleado>?>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }

        })
    }

    fun buscarEmpleados(termino: String, callback: (List<Empleado>?, String?) -> Unit){

        val call = api.buscarEmpleados(termino)
        call.enqueue(object : Callback<List<Empleado>> {
            override fun onResponse(
                call: Call<List<Empleado>?>,
                response: Response<List<Empleado>?>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback(it, null)
                    }?: callback(null, "Respuesta vacia")
                }else{
                    callback(null, "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Empleado>?>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }
        })
    }
}






