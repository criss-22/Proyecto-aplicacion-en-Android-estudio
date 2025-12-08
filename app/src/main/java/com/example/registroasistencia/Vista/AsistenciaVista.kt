package com.example.registroasistencia.Vista

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registroasistencia.Presentador.AsistenciaPress
import com.example.registroasistencia.R
import com.example.registroasistencia.Vista.Contratos.AsistenciaInt

class AsistenciaVista : AppCompatActivity(), AsistenciaInt {

    private lateinit var presenter: AsistenciaPress
    private lateinit var tblAsistencias: TableLayout
    private var idEmpleado = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_asistencia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tblAsistencias = findViewById(R.id.tblAsistencias)

        presenter = AsistenciaPress(this)

        // ID que viene del login:
        idEmpleado = intent.getIntExtra("id", -1)

        if (idEmpleado == -1) {
            Toast.makeText(this, "Error: No se recibió ID del empleado", Toast.LENGTH_LONG).show()
            return
        }

        presenter.cargarAsistencias(idEmpleado)
    }

    override fun asistenciaSuccess(mapa: Map<String, String>) {
         for ((dia, estado) in mapa) {
            val row = TableRow(this)

            val txtDia = TextView(this)
            txtDia.text = dia
            txtDia.setPadding(20, 20, 20, 20)
             txtDia.setBackgroundResource(R.drawable.border)


             val txtEstado = TextView(this)
            txtEstado.text = estado
            txtEstado.setPadding(20, 20, 20, 20)
             txtEstado.setBackgroundResource(R.drawable.border)




             row.addView(txtDia)
            row.addView(txtEstado)

            tblAsistencias.addView(row)
        }
    }

    override fun asistenciaFailure(error: String) {
        Toast.makeText(this, "Error en asistencia: $error", Toast.LENGTH_SHORT).show()
    }
}