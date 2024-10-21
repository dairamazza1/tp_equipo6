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

    private lateinit var rVLanzamiento: RecyclerView
    private lateinit var rVPopular: RecyclerView
    private lateinit var rVMasVotadas: RecyclerView
    private lateinit var rVProximas: RecyclerView
    private lateinit var adapter: PeliculaAdapter
    private lateinit var adapterPopulares: PeliculaAdapter
    private lateinit var adapterMasVotadas: PeliculaAdapter
    private lateinit var adapterProximas: PeliculaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rVLanzamiento = findViewById(R.id.recyclerViewLanzamiento)
        rVLanzamiento.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rVPopular = findViewById(R.id.recyclerViewPopular)
        rVPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rVMasVotadas = findViewById(R.id.recyclerViewMasVotadas)
        rVMasVotadas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rVProximas = findViewById(R.id.recyclerViewProximas)
        rVProximas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        obtenerPeliculas()
    }

    private fun obtenerPeliculas() {
        CoroutineScope(Dispatchers.IO).launch {
            val responseCartelera = Retrofit.webService.obtenerCartelera(Constantes.API_KEY)
            val responseDeGeneros = Retrofit.webServiceGeneros.obtenerGeneros(Constantes.API_KEY)
            val responsePopulares = Retrofit.webService.obtenerPopulares(Constantes.API_KEY)
            val responseMejores = Retrofit.webService.obtenerMejores(Constantes.API_KEY)
            val responseProximas = Retrofit.webService.obtenerProximas(Constantes.API_KEY)

            if (responseCartelera.isSuccessful && responseDeGeneros.isSuccessful) {
                //Acá uso el elvis operator para evitar crasheos y que devuelva una lista y no un null
                val peliculas = responseCartelera.body()?.resultados ?: emptyList()
                val generos = responseDeGeneros.body()?.generosTraidos ?: emptyList()
                val peliculasLanzamiento = responseCartelera.body()?.resultados ?: emptyList()
                val peliculasPopulares = responsePopulares.body()?.resultados ?: emptyList()
                val peliculasMasVotadas = responseMejores.body()?.resultados ?: emptyList()
                val peliculasProximas = responseProximas.body()?.resultados ?: emptyList()

                Log.d("Proximas:", peliculasProximas.toString())

                for (pelicula in peliculas) {
                    val nombresGeneros =
                        StringBuilder() // Usamos un StringBuilder para concatenar los nombres
                    for (idGenero in pelicula.generosId) {
                        // Buscar el nombre del género por el ID
                        val genero = generos.find { it.id == idGenero }
                        if (genero != null) {
                            if (nombresGeneros.isNotEmpty()) {
                                nombresGeneros.append(", ") // Agregar una coma entre géneros
                            }
                            nombresGeneros.append(genero.name)
                        }
                        pelicula.generos = nombresGeneros.toString()
                    }
                }

                // Actualizar la interfaz de usuario en el hilo principal
                runOnUiThread {
                    adapter = PeliculaAdapter(this@MainActivity, peliculasLanzamiento)
                    rVLanzamiento.adapter = adapter

                    adapterPopulares = PeliculaAdapter(this@MainActivity, peliculasPopulares)
                    rVPopular.adapter = adapterPopulares

                    adapterMasVotadas = PeliculaAdapter(this@MainActivity, peliculasMasVotadas)
                    rVMasVotadas.adapter = adapterMasVotadas

                    adapterProximas = PeliculaAdapter(this@MainActivity, peliculasProximas)
                    rVProximas.adapter = adapterProximas

                    adapter.actualizarPeliculas(peliculas)
                    adapterPopulares.actualizarPeliculas(peliculasPopulares)
                    adapterMasVotadas.actualizarPeliculas(peliculasMasVotadas)
                    adapterProximas.actualizarPeliculas(peliculasProximas)
                }
            } else {
                Log.e("Error API", responseCartelera.errorBody()?.string() ?: "Error desconocido")
            }
        }
    }
}
