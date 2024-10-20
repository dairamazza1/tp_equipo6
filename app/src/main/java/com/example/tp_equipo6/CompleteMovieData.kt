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
        //val genero = intent.getStringExtra("genero")
        val sipnosis = intent.getStringExtra("sipnosis")
        val fecha = intent.getStringExtra("fecha")
        val poster = intent.getStringExtra("poster")

        val posterImageView: ImageView = findViewById(R.id.posterImageViewDetalle)
        val tituloTextView: TextView = findViewById(R.id.tituloTextViewDetalle)
        val sipnosisTextView: TextView = findViewById(R.id.sipnosisTextView)


        tituloTextView.text = titulo
        sipnosisTextView.text = sipnosis
        }
    }