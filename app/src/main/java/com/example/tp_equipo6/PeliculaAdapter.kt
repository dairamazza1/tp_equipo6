package com.example.tp_equipo6

import android.content.Context
import android.content.Intent
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
        val txtTitulo: TextView

        init {
            ivPelicula = view.findViewById(R.id.ivPelicula)
            txtTitulo = view.findViewById(R.id.tvNombrePelicula)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pelicula,parent,false)

        return PeliculaViewHolder(view)
    }

    override fun getItemCount() = peliculas.size

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item = peliculas.get(position)
        holder.txtTitulo.text = item.titulo

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, CompleteMovieData::class.java).apply {
                putExtra("id", item.id)
                putExtra("titulo", item.titulo)
                putExtra("genero", item.genero)
            }
            context.startActivity(intent)
        })
    }

}