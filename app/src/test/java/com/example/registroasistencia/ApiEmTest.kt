package com.example.registroasistencia
import com.example.registroasistencia.Modelo.ApiDatos
import com.example.registroasistencia.Modelo.ApiEmpleados
import com.example.registroasistencia.Modelo.DataClass.Departamento
import com.example.registroasistencia.Modelo.DataClass.Empleado
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiEmTest {
    private lateinit var server: MockWebServer
    private lateinit var api: ApiEmpleados
    private lateinit var api2: ApiDatos

        @Before
        fun setup() {
            // se inicia el MockWebServer
            server = MockWebServer()
            server.start()

            // se tiene que crear el retrofit al MockWebServer
            val retrofit = Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // se crear imple de la interfaz ApiEmpleados
            api = retrofit.create(ApiEmpleados::class.java)
            api2 = retrofit.create(ApiDatos::class.java)

        }


        @After
        fun tearDown() {
            server.shutdown()
        }

        @Test
        fun `listaDeEmpleados`() {//para la lista de los empleados el primero es juan
            // se prepara el JSON de respuesta de prueba
            val mockJson = """
            [
                {
                    "id": 1,
                    "Nombre": "Juan",
                    "apaterno": "Lopez",
                    "amaterno": "Diaz",
                    "correo": "juan@mail.com",
                    "telefono": "123456"
                }
            ]
        """.trimIndent()

            // la respuesta en el servidor de mentira
            server.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(mockJson)
            )

            // ejecutar la llamada  y verificar
            val call = api.listarEmpleados()
            val response = call.execute()

            assertTrue("La respuesta no fue exitosa", response.isSuccessful)
            val body = response.body()
            assertEquals(1, body?.size ?: 0)
            assertEquals("Juan", body?.get(0)?.Nombre)
        }
    //este es para el de la lista de los empleados
    @Test
    fun `listasDepartamentoss`() {//el de la listadepartamentos nops tiene que regresar la lista y el primero es el de sistemas

        val mockJson = """
        [
            {
                "Id_Departamento": "1",
                "Departamento": "Sistemas"
            },
            {
                "Id_Departamento": "2",
                "Departamento": "Contabilidad"
            }
        ]
    """.trimIndent()

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockJson)
        )

        val call = api2.listarDepartamentos()//se crea la variable que ocupa el apidatos solo la funcion de listadepartamentos
        val response = call.execute()

        assertTrue(response.isSuccessful)

        val body = response.body()

        assertEquals(2, body?.size ?: 0)
        assertEquals("Sistemas", body?.get(0)?.Departamento)//este es para el nombre
        assertEquals("1", body?.get(0)?.Id_Departamento)//solo es el id
    }

}

//solo verifica si el json lo esta recibiendo lo que le mando solo no mandamos a llamar al api real ya que se crea uno de mentiras
//y recibe el json con lo que se supone que debe de recibir
//ya solo verifica que el api real funcione correctamente
