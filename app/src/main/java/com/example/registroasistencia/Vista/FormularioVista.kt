package com.example.registroasistencia.Vista

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.example.registroasistencia.Modelo.DataClass.Departamento
import com.example.registroasistencia.Modelo.DataClass.Puesto
import com.example.registroasistencia.Modelo.DataClass.TipoUsuario
import com.example.registroasistencia.Presentador.FormularioPres
import com.example.registroasistencia.R
import com.example.registroasistencia.Vista.Contratos.FormularioContrato
import java.io.File

class FormularioVista : AppCompatActivity(), FormularioContrato {

    //Controles Spinner /Combox
    private lateinit var spnDepartamento: Spinner
    private lateinit var spnPuestos: Spinner
    private lateinit var spnTiposUsuario: Spinner
   //Foto
    private lateinit var ivFoto : ImageView

    //Otros controles
    private lateinit var etNombre: EditText
    private lateinit var etPaterno: EditText
    private lateinit var etMaterno: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etPass: EditText
    private lateinit var etTelefono: EditText


    //otros
    private lateinit var presenter : FormularioPres

    private var idDepartamento: String? = null
    private var idPuesto: String? = null
    private var idTipoUsuario: String? = null

    private lateinit var Tomar: Button
    private lateinit var btGuardar: Button
    private lateinit var btEditar: Button
    private lateinit var btBorrar: Button

    //Para la camara
    private lateinit var photoUri: Uri

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                abrirCamara()
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
            }
        }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                //Limpia la imagen anterior
                ivFoto.setImageDrawable(null)

                //Carga la nueva foto
                ivFoto.setImageURI(photoUri)
            }
        }
    //Fin de camara

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fomulario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        idDepartamento = intent.getStringExtra("Id_Departamento")
        idPuesto = intent.getStringExtra("Id_Puesto")
        idTipoUsuario = intent.getStringExtra("Id_Tipo_Usuario")

        //iniciarlizar controles
        spnPuestos=findViewById(R.id.spnPuesto)
        spnDepartamento=findViewById(R.id.snpDepartamento)
        spnTiposUsuario=findViewById(R.id.spnTipo)

        ivFoto=findViewById(R.id.ivFoto)
        etNombre=findViewById(R.id.etNombre)
        etPaterno=findViewById(R.id.etPaterno)
        etMaterno=findViewById(R.id.etMaterno)
        etCorreo=findViewById(R.id.etCorreo)
        etPass=findViewById(R.id.etPass)
        etTelefono=findViewById(R.id.edTelefono)

        Tomar=findViewById(R.id.btTomar)
        btBorrar=findViewById(R.id.btBorrar)
        btEditar=findViewById(R.id.btActualizar)
        btGuardar=findViewById(R.id.btGuardar)


        val idEmpleado = intent.getStringExtra("Id_Empleado")
        if (idEmpleado == null) {
            // No llegaron datos → modo registrar
            btGuardar.visibility = View.VISIBLE
            btEditar.visibility = View.GONE
            btBorrar.visibility = View.GONE
        } else {
            // Llegaron datos → modo editar
            btGuardar.visibility = View.GONE
            btEditar.visibility = View.VISIBLE
            btBorrar.visibility = View.VISIBLE
        }

        //recuperar los datos
        val nombre =intent.getStringExtra("Nombre")
        val apellidoP= intent.getStringExtra("Apellido_Paterno")
        val apellidM=intent.getStringExtra("Apellido_Materno")
        val correo= intent.getStringExtra("Correo")
        val telefono=intent.getStringExtra("Telefono")
        val foto=intent.getStringExtra("foto")
        val contrasena=intent.getStringExtra("Contrasena")





        presenter = FormularioPres(this)

        presenter.CargarPuestos()
        presenter.CargarDepartamentos()
        presenter.CargarTiposUsuarios()



        etNombre.setText(nombre?:"")
        etPaterno.setText(apellidoP?:"")
        etMaterno.setText(apellidM?:"")
        etCorreo.setText(correo?:"")
        etTelefono.setText(telefono?:"")
        etPass.setText("")

        if(!foto.isNullOrBlank()){
            Glide.with(this).load("https://juandios.grupoctic.com/Peliculas/img/$foto").into(ivFoto)
        }
        else{
            ivFoto.setImageResource(R.drawable.imglogin)
        }
        /*
        if (!foto.isNullOrEmpty()) {
            Glide.with(this)
                .load("https://juandios.grupoctic.com/Peliculas/img/$foto")
                .override(200, 200) // limita tamaño máximo para no explotar memoria
                .downsample(DownsampleStrategy.CENTER_INSIDE) // reduce antes de dibujar
                .centerCrop()
                .into(ivFoto)
        } else {
            // Si no hay foto, poner una imagen por defecto
            ivFoto.setImageResource(R.drawable.imglogin)
        }*/








        Tomar.setOnClickListener {
            requestPermission.launch(android.Manifest.permission.CAMERA)
        }


        btEditar.setOnClickListener {
            actualizarEmpleado()
        }

        btGuardar.setOnClickListener { guardarEmpleado() }

        btBorrar.setOnClickListener {
            val idEmpleado = intent.getStringExtra("Id_Empleado")
            if (!idEmpleado.isNullOrEmpty()) {
                presenter.eliminarEmpleado(idEmpleado, this)
            } else {
                Toast.makeText(this, "No se encontró ID del empleado", Toast.LENGTH_SHORT).show()
            }
        }





    }


    private fun guardarEmpleado() {
        presenter.guardarEmpleado(
            etNombre.text.toString(),
            etPaterno.text.toString(),
            etMaterno.text.toString(),
            etCorreo.text.toString(),
            etTelefono.text.toString(),
            etPass.text.toString(),
            idTipoUsuario ?: "",
            idDepartamento ?: "",
            idPuesto ?: "",
            if (::photoUri.isInitialized) photoUri else null,
            this
        )
    }



    //Funcion actulizar
    private fun actualizarEmpleado() {
        val id = intent.getStringExtra("Id_Empleado") ?: return

        val nombre = etNombre.text.toString()
        val apaterno = etPaterno.text.toString()
        val amaterno = etMaterno.text.toString()
        val correo = etCorreo.text.toString()
        val telefono = etTelefono.text.toString()
        val contrasena = etPass.text.toString()

        val tipoUsuario = idTipoUsuario ?: ""
        val departamento = idDepartamento ?: ""
        val puesto = idPuesto ?: ""

        presenter.actualizarEmpleado(
            id,
            nombre,
            apaterno,
            amaterno,
            correo,
            telefono,
            contrasena,
            tipoUsuario,
            departamento,
            puesto,
            if (this::photoUri.isInitialized) photoUri else null,
            this
        )
    }






    //Mertodos de camara
    private fun createImageFile(): Uri {
        val dir = externalCacheDir ?: cacheDir
        val file = File.createTempFile("temp_photo", ".jpg", dir)

        return FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            file
        )
    }
    private fun abrirCamara() {
        photoUri = createImageFile()
        cameraLauncher.launch(photoUri)
    }


    //Metodos del contrato
    override fun mostrarDepartamentos(lista: List<Departamento>) {
        // Agregamos "Selecciona Departamento" al inicio
        val datos = listOf("Selecciona Departamento") + lista.map { it.Departamento }

        val adapter = ArrayAdapter(this, R.layout.spinner_item, datos)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spnDepartamento.adapter = adapter

        val index = lista.indexOfFirst { it.Id_Departamento == idDepartamento }
        if (index >= 0) {
            spnDepartamento.setSelection(index + 1)
        } else {
            spnDepartamento.setSelection(0)
        }

        spnDepartamento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // 3. Si elige la posición 0 (Título), no hay ID. Si no, restamos 1.
                if (position == 0) {
                    idDepartamento = null
                } else {
                    idDepartamento = lista[position - 1].Id_Departamento
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) { idDepartamento = null }
        }
    }

    override fun mostrarPuestos(lista: List<Puesto>) {
        val datos = listOf("Selecciona Puesto") + lista.map { it.Puesto }

        val adapter = ArrayAdapter(this, R.layout.spinner_item, datos)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spnPuestos.adapter = adapter

        val index = lista.indexOfFirst { it.Id_Puesto == idPuesto }
        if (index >= 0) spnPuestos.setSelection(index + 1) else spnPuestos.setSelection(0)

        spnPuestos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    idPuesto = null
                } else {
                    idPuesto = lista[position - 1].Id_Puesto
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) { idPuesto = null }
        }
    }

    override fun mostrarTiposUsuario(lista: List<TipoUsuario>) {
        val datos = listOf("Selecciona Tipo") + lista.map { it.Usuario }

        val adapter = ArrayAdapter(this, R.layout.spinner_item, datos)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spnTiposUsuario.adapter = adapter

        val index = lista.indexOfFirst { it.Id_Tipo_Usuario == idTipoUsuario }
        if (index >= 0) spnTiposUsuario.setSelection(index + 1) else spnTiposUsuario.setSelection(0)

        spnTiposUsuario.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    idTipoUsuario = null
                } else {
                    idTipoUsuario = lista[position - 1].Id_Tipo_Usuario
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) { idTipoUsuario = null }
        }
    }


    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun mostrarMensajeExito(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}