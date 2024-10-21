package com.example.tp_equipo6

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PeliculaAdapter(private val context: Context, private var peliculas: List<Pelicula>) :
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    inner class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.ivPelicula)
        val tituloTextView: TextView = itemView.findViewById(R.id.tvNombrePelicula)

        fun bind(pelicula: Pelicula) {
            tituloTextView.text = pelicula.titulo

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${pelicula.posterPath}")
                .into(posterImageView)

            itemView.setOnClickListener {
                // Al hacer click, lanzamos la actividad de detalles
                val intent = Intent(context, CompleteMovieData::class.java)
                intent.putExtra("titulo", pelicula.titulo)
                intent.putExtra("poster", pelicula.posterPath)
                intent.putExtra("genero", pelicula.generos)
                intent.putExtra("sipnosis", pelicula.sipnosis)
                intent.putExtra("fecha", pelicula.fechaDeSalida)
                intent.putExtra("cantidadDeVotos", pelicula.cantidadDeVotos)

                context.startActivity(intent)
            }
        }
    }
    fun actualizarPeliculas(nuevasPeliculas: List<Pelicula>) {
        peliculas = nuevasPeliculas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        holder.bind(peliculas[position])
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

}