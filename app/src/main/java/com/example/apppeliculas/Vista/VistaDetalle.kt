package com.example.apppeliculas.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.apppeliculas.Modelo.DetalleModel
import com.example.apppeliculas.Presentador.DetallePresenter
import com.example.apppeliculas.R

class VistaDetalle : AppCompatActivity(),DetalleContrac {
    private lateinit var imgfoto: ImageView
    private lateinit var txtnombre: TextView
    private lateinit var txtdescripcion: TextView
    private lateinit var txtsinopsis: TextView
    private lateinit var btnReproducir: Button
    private lateinit var presenter: DetallePresenter
    private var videoUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgfoto = findViewById(R.id.imgDetalle)
        txtnombre = findViewById(R.id.txtNombreDetalle)
        txtdescripcion = findViewById(R.id.txtDescripcionDetalle)
        txtsinopsis = findViewById(R.id.txtSinopsis)
        btnReproducir = findViewById(R.id.btnReproducir)

        presenter = DetallePresenter(this)

        // Convertir los extras a un Map<String,String?>
        val extras = intent.extras?.keySet()?.associateWith { intent.extras?.getString(it) } ?: emptyMap()
        presenter.cargarDatos(extras)

        btnReproducir.setOnClickListener { presenter.onReproducirClicked(videoUrl) }
    }

    override fun mostrarPelicula(pelicula: DetalleModel) {
        txtnombre.text = pelicula.nombre
        txtdescripcion.text = pelicula.descripcion
        txtsinopsis.text = pelicula.sinopsis
        videoUrl = pelicula.video

        Glide.with(this)
            .load("https://cristopher.grupoctic.com/Peliculas/img/${pelicula.imagen}")
            .into(imgfoto)
    }

    override fun reproducirVideo(videoUrl: String) {
        val intent = Intent(this, reproducir::class.java)
        intent.putExtra("pelicula_video", videoUrl)
        startActivity(intent)
    }
}