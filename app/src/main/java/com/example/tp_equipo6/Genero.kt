package com.example.tp_equipo6

import com.google.gson.annotations.SerializedName

data class Genero(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)

