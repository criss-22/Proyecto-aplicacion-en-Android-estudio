package com.example.registroasistencia.Vista

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView

import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registroasistencia.Modelo.MisionVisionRespone
import com.example.registroasistencia.Presentador.MisionVisionPress
import com.example.registroasistencia.R
import com.example.registroasistencia.Vista.Contratos.MisionVisionInt
import com.bumptech.glide.Glide

class misionVision : AppCompatActivity(), MisionVisionInt {
    private lateinit var presentar: MisionVisionPress

    private lateinit var txtVision: TextView
    private lateinit var txtMision: TextView
    private lateinit var txtinfo: TextView
    private lateinit var imgvision: ImageView
    private lateinit var imgmision: ImageView
    private lateinit var imginfo: ImageView

    //private lateinit var progress: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mision_vision)
        presentar = MisionVisionPress(this)
        txtMision = findViewById(R.id.txtMision)
        txtVision = findViewById(R.id.txtVision)
        txtinfo = findViewById(R.id.txtinfo)

        imgmision = findViewById(R.id.imgmision)
        imgvision = findViewById(R.id.imgvision)
        imginfo = findViewById(R.id.imginfo)

        presentar.cargarInfo()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

   // private val URL =  "https://isai.grupoctic.com/BocetoProyect/MisionVision/images/"
   private val URL =  "https://juandios.grupoctic.com/Peliculas/img/"

    override fun mostrarInfo(datos: MisionVisionRespone) {
        txtMision.text = datos.mision
        txtVision.text = datos.vision
        txtinfo.text = datos.info

        Glide.with(this)
            .load(URL + datos.img_mision)
            .into(imgmision)

        Glide.with(this)
            .load(URL + datos.img_vision)
            .into(imgvision)

        Glide.with(this)
            .load(URL + datos.iminfo)
            .into(imginfo)


    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}