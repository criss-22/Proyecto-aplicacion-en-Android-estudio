package com.example.registroasistencia.Presentador

import android.app.Activity
import android.net.Uri
import com.example.registroasistencia.Modelo.FormularioMod
import com.example.registroasistencia.Vista.Contratos.FormularioContrato
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class FormularioPres(private val view: FormularioContrato) {

    private  val model= FormularioMod()


    fun CargarDepartamentos(){
        model.listarDepartamentos { lista, error ->
            if(lista!=null){
                view.mostrarDepartamentos(lista)
            }else{
                view.mostrarError(error ?: "Error desconocido")
            }
        }
    }

    fun CargarPuestos(){
        model.listarPuestos { lista, error ->
            if(lista!=null){
                view.mostrarPuestos(lista)
            }else{
                view.mostrarError(error ?: "Error desconocido")
            }
        }
    }

    fun CargarTiposUsuarios(){
        model.listarTiposUsuario { lista, error ->
            if(lista!=null){
                view.mostrarTiposUsuario(lista)
            }else{
                view.mostrarError(error ?: "Error desconocido")
            }
        }
    }


    private fun rb(s: String): RequestBody =
        RequestBody.create(MultipartBody.FORM, s)

    fun actualizarEmpleado(
        id: String,
        nombre: String,
        apaterno: String,
        amaterno: String,
        correo: String,
        telefono: String,
        contrasena: String,
        tipoUsuario: String,
        departamento: String,
        puesto: String,
        fotoUri: Uri?,
        activity: Activity
    ) {
        // Convertimos foto → MultipartBody.Part si existe
        val fotoPart = if (fotoUri != null) {
            val inputStream = activity.contentResolver.openInputStream(fotoUri)
            val tempFile = File(activity.cacheDir, "temp_photo.jpg")
            inputStream?.use { input ->
                tempFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            val reqFile = tempFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("foto", tempFile.name, reqFile)
        } else null

        model.actualizarEmpleado(
            rb(id),
            rb(nombre),
            rb(apaterno),
            rb(amaterno),
            rb(correo),
            rb(telefono),
            rb(contrasena),
            rb(tipoUsuario),
            rb(departamento),
            rb(puesto),
            fotoPart
        ) { success, msg ->
            if (success) view.mostrarMensajeExito(msg)
            else view.mostrarError(msg)
        }
    }

    fun guardarEmpleado(
        nombre: String,
        apaterno: String,
        amaterno: String,
        correo: String,
        telefono: String,
        contrasena: String,
        tipoUsuario: String,
        departamento: String,
        puesto: String,
        fotoUri: Uri?,
        activity: Activity
    ) {
        val fotoPart = if (fotoUri != null) {
            val inputStream = activity.contentResolver.openInputStream(fotoUri)
            val tempFile = File(activity.cacheDir, "temp_photo.jpg")
            inputStream?.use { input -> tempFile.outputStream().use { output -> input.copyTo(output) } }
            val reqFile = tempFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("foto", tempFile.name, reqFile)
        } else null

        model.crearEmpleado(
            rb(nombre), rb(apaterno), rb(amaterno),
            rb(correo), rb(telefono), rb(contrasena),
            rb(tipoUsuario), rb(departamento), rb(puesto),
            fotoPart
        ) { success, msg ->
            if (success) view.mostrarMensajeExito(msg)
            else view.mostrarError(msg)
        }
    }


    fun eliminarEmpleado(idEmpleado: String, activity: Activity) {
        val idInt = try {
            idEmpleado.toInt()
        } catch (e: NumberFormatException) {
            view.mostrarError("ID de empleado inválido")
            return
        }

        model.eliminarEmpleado(idInt) { success, msg ->
            if (success) {
                view.mostrarMensajeExito(msg)
                activity.finish() // regresa a la vista anterior
            } else {
                view.mostrarError(msg)
            }
        }
    }










}