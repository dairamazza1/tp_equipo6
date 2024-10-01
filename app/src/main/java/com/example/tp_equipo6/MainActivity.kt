package com.example.tp_equipo6

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rvPeliculaTendencias : RecyclerView
    lateinit var rvPeliculaNuevas : RecyclerView
    lateinit var rvPeliculaParaTi : RecyclerView
    lateinit var rvPeliculaAccion: RecyclerView
    lateinit var rvPeliculaTerror: RecyclerView
    lateinit var peliculasAdapter: PeliculaAdapter
    lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvPeliculaTendencias = findViewById(R.id.rvPeliculasTendencia)
        peliculasAdapter = PeliculaAdapter(getPeliculas(),this)
        rvPeliculaTendencias.adapter = peliculasAdapter

        rvPeliculaNuevas = findViewById(R.id.rvPeliculasNuevas)
        peliculasAdapter = PeliculaAdapter(getPeliculasNuevas(),this)
        rvPeliculaNuevas.adapter = peliculasAdapter

        rvPeliculaParaTi = findViewById(R.id.rvPeliculasParaTi)
        peliculasAdapter = PeliculaAdapter(getPeliculas(),this)
        rvPeliculaParaTi.adapter = peliculasAdapter

        rvPeliculaAccion = findViewById(R.id.rvPeliculasAccion)
        peliculasAdapter = PeliculaAdapter(getPeliculasAccion(),this)
        rvPeliculaAccion.adapter = peliculasAdapter

        rvPeliculaTerror = findViewById(R.id.rvPeliculasTerror)
        peliculasAdapter = PeliculaAdapter(getPeliculasTerror(),this)
        rvPeliculaTerror.adapter = peliculasAdapter

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)
    }

    private fun getPeliculasTerror(): MutableList<Pelicula> {
        var peliculas: MutableList<Pelicula> = ArrayList()

        peliculas.add(Pelicula(1, "Alien romulus", "Terror"))
        peliculas.add(Pelicula(2, "Viernes 13", "Terror"))
        peliculas.add(Pelicula(3, "IT", "Terror"))
        peliculas.add(Pelicula(4, "Halloween", "Terror"))
        peliculas.add(Pelicula(5, "Scream", "Terror"))
        peliculas.add(Pelicula(6, "Terrifier", "Terror"))
        return peliculas
    }

    private fun getPeliculasAccion(): MutableList<Pelicula> {
        var peliculas: MutableList<Pelicula> = ArrayList()
        peliculas.add(Pelicula(1, "Dedpool y wolverine", "Accion"))
        peliculas.add(Pelicula(2, "El Hombre Araña", "Accion"))
        peliculas.add(Pelicula(3, "Mi villano favorito 4", "Accion"))
        peliculas.add(Pelicula(4, "Star Wars", "Accion"))
        peliculas.add(Pelicula(5, "IronMan", "Accion"))
        peliculas.add(Pelicula(6, "The batman", "Accion"))

        return peliculas
    }

    private fun getPeliculasNuevas(): MutableList<Pelicula> {
        var peliculas: MutableList<Pelicula> = ArrayList()
        peliculas.add(Pelicula(1, "Dedpool y wolverine", "Accion"))
        peliculas.add(Pelicula(2, "Intensamente 2", "Infantil"))
        peliculas.add(Pelicula(3, "Mi villano favorito 4", "Accion"))
        peliculas.add(Pelicula(4, "Alien romulus", "Terror"))
        peliculas.add(Pelicula(5, "Beetlejuice", "Comedia"))
        peliculas.add(Pelicula(6, "The batman", "Accion"))

        return peliculas
    }

    private fun getPeliculas(): MutableList<Pelicula> {
        var peliculas: MutableList<Pelicula> = ArrayList()
        peliculas.add(Pelicula(1, "El Hombre Araña", "Accion"))
        peliculas.add(Pelicula(2, "The Truman Show", "Comedia"))
        peliculas.add(Pelicula(3, "Viernes 13", "Terror"))
        peliculas.add(Pelicula(4, "Star Wars", "Accion"))
        peliculas.add(Pelicula(5, "IronMan", "Accion"))
        peliculas.add(Pelicula(6, "Todopoderoso", "Comedia"))

        return peliculas

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cerrarsesion,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.itemCerrarSesion){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}