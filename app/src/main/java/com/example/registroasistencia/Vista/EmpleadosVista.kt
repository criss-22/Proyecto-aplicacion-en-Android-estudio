package com.example.registroasistencia.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registroasistencia.Modelo.DataClass.Empleado
import com.example.registroasistencia.Presentador.EmpleadosPre
import com.example.registroasistencia.R
import com.example.registroasistencia.Vista.Adaptadores.EmpleadoAdapter
import com.example.registroasistencia.Vista.Contratos.EmpleadosInt

class EmpleadosVista : AppCompatActivity(), EmpleadosInt {

   

    private  lateinit var presenter : EmpleadosPre
    private lateinit var  recyclerView: RecyclerView
    private lateinit var adapter: EmpleadoAdapter
    private lateinit var searchView: SearchView

    private lateinit var Agregar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recicler)
        searchView= findViewById(R.id.swBuscar)
        Agregar=findViewById(R.id.btnAgregar)

        adapter= EmpleadoAdapter(listOf())
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter

        presenter = EmpleadosPre(this)
        presenter.cargarListado()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { presenter.buscarEmpleado(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { presenter.buscarEmpleado(it) }
                return true
            }
        })

        Agregar.setOnClickListener {
            val intent = Intent(this, FormularioVista::class.java)
            startActivity(intent)
        }


    }

    override fun mostrarListado(lista: List<Empleado>) {
        adapter.actualizarLista(lista)
    }

    override fun mostrarBusqueda(lista: List<Empleado>) {
        adapter.actualizarLista(lista)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}