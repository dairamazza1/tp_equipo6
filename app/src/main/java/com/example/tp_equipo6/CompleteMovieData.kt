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
        val votos = intent.getIntExtra("cantidadDeVotos",0)
        val votoProximamente = "Próximamente"
        val puntuacionPromedio = intent.getFloatExtra("puntuacionPromedio",0f)




        val posterImageView: ImageView = findViewById(R.id.posterImageViewDetalle)
        val tituloTextView: TextView = findViewById(R.id.tituloTextViewDetalle)
        val sipnosisTextView: TextView = findViewById(R.id.sipnosisTextView)
        val fechaTextView: TextView = findViewById(R.id.fechaTextView)
        val generoTextView: TextView = findViewById(R.id.generoTextView)
        val votosTextView: TextView = findViewById(R.id.cantidadVotosTextView)
        val ratingTextView : TextView = findViewById(R.id.tvRating)


        val estrella1 : ImageView = findViewById(R.id.estrella1)
        val estrella2 : ImageView = findViewById(R.id.estrella2)
        val estrella3 : ImageView = findViewById(R.id.estrella3)
        val estrella5 : ImageView = findViewById(R.id.estrella5)
        val estrella6 : ImageView = findViewById(R.id.estrella6)


        tituloTextView.text = titulo
        sipnosisTextView.text = sipnosis
        fechaTextView.text = fecha
        generoTextView.text = genero
        if(votos == 0){
            votosTextView.text = votoProximamente
        }else{
            votosTextView.text = votos.toString()
        }



        if(puntuacionPromedio >= 2f){
            estrella1.setImageResource(R.drawable.baseline_star_rate_24)
            if(puntuacionPromedio >= 4f){
                estrella2.setImageResource(R.drawable.baseline_star_rate_24)
                if(puntuacionPromedio >= 6f){
                    estrella3.setImageResource(R.drawable.baseline_star_rate_24)
                    if(puntuacionPromedio >= 8f){
                        estrella5.setImageResource(R.drawable.baseline_star_rate_24)
                        if(puntuacionPromedio == 10f){
                            estrella6.setImageResource(R.drawable.baseline_star_rate_24)
                        }
                    }
                }
            }
        }
        ratingTextView.text = puntuacionPromedio.toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${poster}")
            .into(posterImageView)

        }
    }