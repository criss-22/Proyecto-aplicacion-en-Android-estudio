package com.example.registroasistencia.Presentador

import com.example.registroasistencia.Modelo.EmpleadosMod
import com.example.registroasistencia.Vista.Contratos.EmpleadosInt

class EmpleadosPre (private  val view: EmpleadosInt ){
    private val model= EmpleadosMod()

    fun cargarListado(){
        model.listarEmpleados { lista, error ->
            if(lista!=null){
                view.mostrarListado(lista)
            }else{
                view.mostrarError(error ?: "Error desconocido")
            }
        }
    }

    fun buscarEmpleado(termino: String){
        model.buscarEmpleados(termino){lista, error ->
            if (lista != null) {
                view.mostrarBusqueda(lista)
            } else {
                view.mostrarError(error ?: "Error desconocido")
            }
        }
    }
}