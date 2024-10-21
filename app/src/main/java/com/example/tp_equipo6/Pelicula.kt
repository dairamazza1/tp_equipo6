package com.example.tp_equipo6

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Pelicula(@SerializedName("id") var id: Int,
                    @SerializedName("original_language") var lenguajeOriginal: String,
                    @SerializedName("original_title") var tituloOriginal: String,
                    @SerializedName("overview") var sipnosis: String,
                    @SerializedName("popularity") var popularidad: Float,
                    @SerializedName("poster_path") var posterPath: String,
                    @SerializedName("release_date") var fechaDeSalida: String,
                    @SerializedName("title") var titulo: String,
                    @SerializedName("vote_average") var puntuacionPromedio: Float,
                    @SerializedName("vote_count") var cantidadDeVotos: Int,
                    @SerializedName("adult") var paraAdultos: String,
                    @SerializedName("backdrop_path") var backdropPath: String,
                    @SerializedName("genre_ids") var generosId: List<Int>,
                    var generos: String
)
