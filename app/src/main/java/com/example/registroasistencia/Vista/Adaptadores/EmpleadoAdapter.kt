package com.example.registroasistencia.Vista.Adaptadores


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.registroasistencia.Modelo.DataClass.Empleado
import com.example.registroasistencia.R
import com.example.registroasistencia.Vista.FormularioVista


class EmpleadoAdapter(private  var lista: List<Empleado>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tipo_cabeza=0
    private val tipo_dato=1

    class cabezaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvFoto: TextView = itemView.findViewById(R.id.tvFoto)
    }

    class datoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvIdEm: TextView = itemView.findViewById(R.id.tvIdEm)
        val tvNombreEm: TextView = itemView.findViewById(R.id.tvNombreEm)
        val ivFotoEm: ImageView = itemView.findViewById(R.id.ivFotoEm)

    }

    override fun getItemViewType(position: Int): Int {
        return if(position==0) tipo_cabeza else tipo_dato
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if(viewType==tipo_cabeza){
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cabezera, parent, false)
            cabezaViewHolder(view)
        }else{
            val view= LayoutInflater.from((parent.context))
                .inflate(R.layout.empleados, parent, false)
            datoViewHolder(view)
        }

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is cabezaViewHolder) {
            holder.tvId.text = "ID"
            holder.tvNombre.text = "Nombre"
            holder.tvFoto.text = "Foto"
        } else if (holder is datoViewHolder) {
            val empleado = lista.getOrNull(position - 1) ?: return
            holder.tvIdEm.text = empleado.Id_Empleado
            holder.tvNombreEm.text = empleado.Nombre

            // Carga segura de imagen
            val urlFoto = empleado.foto
            Glide.with(holder.itemView.context)
                .load(if (!urlFoto.isNullOrEmpty()) "https://juandios.grupoctic.com/Peliculas/img/$urlFoto" else R.drawable.imglogin)
                .into(holder.ivFotoEm)

            //aqui
            holder.itemView.setOnClickListener {

                val ctx = holder.itemView.context
                val intent = Intent(ctx, FormularioVista::class.java).apply {
                    putExtra("Id_Empleado", empleado.Id_Empleado)
                    putExtra("Nombre", empleado.Nombre)
                    putExtra("Apellido_Paterno", empleado.Apellido_Paterno)
                    putExtra("Apellido_Materno", empleado.Apellido_Materno)
                    putExtra("Correo", empleado.Correo)
                    putExtra("Telefono", empleado.Telefono)
                    putExtra("foto", empleado.foto)
                    putExtra("Id_Departamento", empleado.Id_Departamento)
                    putExtra("Id_Puesto", empleado.Id_Puesto)
                    putExtra("Id_Tipo_Usuario", empleado.Id_Tipo_Usuario)
                    putExtra("Contrasena", empleado.Contrasena)
                }

                ctx.startActivity(intent)
            }
            //aca
        }
    }

    override fun getItemCount(): Int = lista.size + 1

    fun actualizarLista(nuevaLista: List<Empleado>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

}