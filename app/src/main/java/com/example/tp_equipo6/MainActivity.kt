package com.example.tp_equipo6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var vistaPelicula: RecyclerView
    private lateinit var adapter: PeliculaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vistaPelicula = findViewById(R.id.vistaPelicula)
        vistaPelicula.layoutManager = LinearLayoutManager(this)

        obtenerPeliculas()
    }

    private fun obtenerPeliculas() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = Retrofit.webService.obtenerPopulares(Constantes.API_KEY)

            if (response.isSuccessful) {
                //Acá uso el elvis operator para evitar crasheos y que devuelva una lista y no un null
                val peliculas = response.body()?.resultados ?: emptyList()
                Log.d("Peliculas", peliculas.toString())

                // Actualizar la interfaz de usuario en el hilo principal
                runOnUiThread {
                    adapter = PeliculaAdapter(this@MainActivity, peliculas)
                    vistaPelicula.adapter = adapter
                    adapter.actualizarPeliculas(peliculas)
                }
            }else{
                Log.e("Error API", response.errorBody()?.string() ?: "Error desconocido")
            }

        }
    }
}
