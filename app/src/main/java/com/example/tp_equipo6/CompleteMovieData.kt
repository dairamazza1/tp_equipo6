package com.example.tp_equipo6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CompleteMovieData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_movie_data)

        val titulo = intent.getStringExtra("titulo")
        val genero = intent.getStringExtra("genero")
        val sipnosis = intent.getStringExtra("sipnosis")
        val fecha = intent.getStringExtra("fecha")
        val poster = intent.getStringExtra("poster")
        val votos = intent.getFloatExtra("CantidadDeVotos",0f)
        val votoProximamente = "Próximamente"

        val posterImageView: ImageView = findViewById(R.id.posterImageViewDetalle)
        val tituloTextView: TextView = findViewById(R.id.tituloTextViewDetalle)
        val sipnosisTextView: TextView = findViewById(R.id.sipnosisTextView)
        val fechaTextView: TextView = findViewById(R.id.fechaTextView)
        val generoTextView: TextView = findViewById(R.id.generoTextView)
        val votosTextView: TextView = findViewById(R.id.cantidadVotosTextView)


        tituloTextView.text = titulo
        sipnosisTextView.text = sipnosis
        fechaTextView.text = fecha
        generoTextView.text = genero
        if(votos == 0f){
            votosTextView.text = votoProximamente
        }else{
            votosTextView.text = votos.toString()
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${poster}")
            .into(posterImageView)

        }
    }