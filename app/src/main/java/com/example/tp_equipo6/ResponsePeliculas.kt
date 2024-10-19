package com.example.tp_equipo6

import com.google.gson.annotations.SerializedName

data class ResponsePeliculas(
    @SerializedName("results")
    var resultados: List<Pelicula>
)
