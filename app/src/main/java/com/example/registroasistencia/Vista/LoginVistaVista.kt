package com.example.registroasistencia.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registroasistencia.Modelo.DataClass.LoginResponse
import com.example.registroasistencia.Presentador.LoginPress
import com.example.registroasistencia.R
import com.example.registroasistencia.Test
import com.example.registroasistencia.Vista.Contratos.LoginInt


class LoginVistaVista : AppCompatActivity(), LoginInt {
    private lateinit var presenter: LoginPress
    private lateinit var edCorreo: EditText
    private lateinit var edPass: EditText
    private lateinit var btAcceder: Button
    ///no se por que no funciona puede ser otra cosa vaya como hago que se vea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)
        val test = Test
        //test.probarListar()
        //test.probarBuscar("isa")
        //test.probarCrear(this)
        //test.probarActualizar(this, false)
        //test.probarEliminar()

        //test.probarListarDepartamentos()
        // test.probarListarPuestos()
        // test.probarListarTiposUsuario()

        //test.probarMisionVision()
        //test.probarLogin("adan", "1")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edCorreo = findViewById(R.id.edCorreo)
        edPass = findViewById(R.id.edPass)
        btAcceder = findViewById(R.id.btAcceder)


        presenter = LoginPress(this)

        btAcceder.setOnClickListener {
            val email = edCorreo.text.toString()
            val password = edPass.text.toString()
            presenter.acceder(email, password)
        }
    }

    override fun accederSuccess(datos: LoginResponse) {

        Toast.makeText(this, "Acceso OK: ${datos.mensaje}", Toast.LENGTH_SHORT).show()

        // Navegar a la vista "Inicio" (la crearás). Envío tipo de usuario:
        val intent = Intent(this, MenuVista::class.java)
        intent.putExtra("tipoUsuario", datos.tipo_usuario ?: -1)
        intent.putExtra("id", datos.id?: -1)
        startActivity(intent)
        finish()
    }

    override fun accederFailure(error: String) {

        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }


}