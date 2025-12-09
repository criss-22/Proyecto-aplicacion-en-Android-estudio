package com.example.registroasistencia

import android.util.Log
import org.junit.Test

class ApiTest {
    @Test
    fun testListarSync() {
        val respuesta = T2.probarListarSync()
        Log.d("ApiTest", "Codigo = ${respuesta.code()}")
        Log.d("ApiTest", "Body   = ${respuesta.body()}")
    }

    @Test
    fun testBuscarSync() {
        val respuesta = T2.probarBuscarSync("Pedro")
        Log.d("ApiTest", "BUSCAR - Codigo = ${respuesta.code()}")
        Log.d("ApiTest", "BUSCAR - Body   = ${respuesta.body()}")
    }

    @Test
    fun testCrearSync() {
        val context = androidx.test.platform.app.InstrumentationRegistry
            .getInstrumentation()
            .targetContext

        val respuesta = T2.probarCrearSync(context)
        Log.d("ApiTest", "CREAR - Codigo = ${respuesta.code()}")
        Log.d("ApiTest", "CREAR - Body   = ${respuesta.body()}")
    }

}