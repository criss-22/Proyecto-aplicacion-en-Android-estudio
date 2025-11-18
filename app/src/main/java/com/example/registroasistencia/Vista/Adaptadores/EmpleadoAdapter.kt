package com.example.registroasistencia.Vista.Adaptadores


import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.registroasistencia.Modelo.Empleado
import com.example.registroasistencia.R
import okhttp3.internal.http2.Header
import org.w3c.dom.Text


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
// FUTURO LISTENER AQUÍ
        // init {
        //     itemView.setOnClickListener {
        //         // Aquí enviará: empleado.ID, nombre, imagen, y TODOS los datos del dataclass
        //     }
        // }
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

            holder.itemView.setOnClickListener {
                // Aquí puedes enviar datos a otra Activity
            }
        }
    }

    override fun getItemCount(): Int = lista.size + 1

    fun actualizarLista(nuevaLista: List<Empleado>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

}