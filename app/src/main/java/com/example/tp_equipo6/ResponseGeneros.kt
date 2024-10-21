package com.example.tp_equipo6

import com.google.gson.annotations.SerializedName

data class ResponseGeneros(
    @SerializedName("genres")
    var generosTraidos: List<Genero>
)
