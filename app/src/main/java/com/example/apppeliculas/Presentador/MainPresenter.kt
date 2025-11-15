package com.example.apppeliculas.Presentador

import com.example.apppeliculas.Modelo.bdpelis
import com.example.apppeliculas.Vista.MainContrac
import com.example.apppeliculas.Vista.ifaceApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(private val view: MainContrac) {

    private val apiService: ifaceApiService

    init {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://cristopher.grupoctic.com/Peliculas/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }

    fun obtenerPeliculas() {
        apiService.obtenerPeliculas().enqueue(object : Callback<List<bdpelis>> {
            override fun onResponse(call: Call<List<bdpelis>>, response: Response<List<bdpelis>>) {
                if (response.isSuccessful) {
                    response.body()?.let { peliculas ->
                        view.mostrarPeliculas(peliculas)
                    } ?: run {
                        view.mostrarError("Error: ${response.message()}")
                    }
                } else {
                    view.mostrarError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<bdpelis>>, t: Throwable) {
                view.mostrarError("Error: ${t.message}")
            }
        })
    }
}