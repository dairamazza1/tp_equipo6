package com.example.tp_equipo6

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CompleteMovieData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_movie_data)

        // Obtener los datos de la película desde el Intent
        val id = intent.getIntExtra("id", 0)
        val nombre = intent.getStringExtra("titulo")
        val genero = intent.getStringExtra("genero")

        // Obtener las vistas del XML
        val tvTitulo = findViewById<TextView>(R.id.titulo)
        val tvGenero = findViewById<TextView>(R.id.genero)

        // Asignar los datos a las vistas
        tvTitulo.text = nombre
        tvGenero.text = genero
    }
}