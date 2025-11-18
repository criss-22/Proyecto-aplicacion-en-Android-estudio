package com.example.registroasistencia.Vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registroasistencia.R

class Menu : AppCompatActivity() {
    private lateinit var tvTipoUsuario: TextView

    private lateinit var btRegistro: Button
    private lateinit var btAsistencia: Button
    private lateinit var btMisVis: Button
    private lateinit var btConocenos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvTipoUsuario = findViewById(R.id.tvTipoUsuario)
        btAsistencia = findViewById(R.id.btAsistencia)
        btRegistro = findViewById(R.id.btRegistro)
        btMisVis = findViewById(R.id.btMisVis)
        btConocenos = findViewById(R.id.btConocenos)


        // Recuperamos el dato recibido desde el Login
        val tipoUsuario = intent.getIntExtra("tipoUsuario", -1)
        val id = intent.getIntExtra("id", -1)



        //Asignamos el valor al TextView
        tvTipoUsuario.text = "Tipo de usuario: $tipoUsuario"


        val rolTexto = when (tipoUsuario) {
            1 -> "Empleado"
            2 -> "Administrador"
            else -> "Desconocido"
        }

        tvTipoUsuario.text = "Rol: $rolTexto"

        if (tipoUsuario == 1) {
            btRegistro.visibility = View.GONE
        }
        /*when (tipoUsuario) {
    1 -> { // Empleado
        btRegistro.visibility = View.GONE
        // Los demás botones visibles
    }
    2 -> { // Admin
        btRegistro.visibility = View.VISIBLE
    }
    else -> {
        // Si por alguna razón no hay tipo, puedes bloquear todo o mostrar mensaje
    }
}
*/



        btRegistro.setOnClickListener {
            val intent = Intent(this, registro::class.java)
           // intent.putExtra("id", id)
            startActivity(intent)
        }
        btAsistencia.setOnClickListener {
            val intent = Intent(this, asistencia::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        btMisVis.setOnClickListener {
            val intent = Intent(this, misionVision::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        btConocenos.setOnClickListener {
            val intent = Intent(this, ConocenosRepro::class.java)
            //intent.putExtra("id", id)
            intent.putExtra("nombreVideo", id)
            startActivity(intent)
        }

    }
}