package com.example.tp_equipo6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter(var peliculas : MutableList<Pelicula>,var context: Context):
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){
    class PeliculaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivPelicula: ImageView
        val txtNombre: TextView

        init {
            ivPelicula = view.findViewById(R.id.ivPelicula)
            txtNombre = view.findViewById(R.id.tvNombrePelicula)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pelicula,parent,false)

        return PeliculaViewHolder(view)
    }

    override fun getItemCount() = peliculas.size

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item = peliculas.get(position)
        holder.txtNombre.text = item.nombre

        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, item.nombre, Toast.LENGTH_SHORT).show()
        })
    }

}