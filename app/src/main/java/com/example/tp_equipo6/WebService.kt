package com.example.tp_equipo6

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET("now_playing")
    suspend fun obtenerCartelera(
        @Query("api_key") apiKey: String
    ): retrofit2.Response<ResponsePeliculas>

    @GET("popular")
    suspend fun obtenerPopulares(
        @Query("api_key") apiKey: String
    ): retrofit2.Response<ResponsePeliculas>

    @GET("top_rated")
    suspend fun obtenerMejores(
        @Query("api_key") apiKey: String
    ): retrofit2.Response<ResponsePeliculas>
    @GET("upcoming")
    suspend fun obtenerProximas(
        @Query("api_key") apiKey: String
    ): retrofit2.Response<ResponsePeliculas>

    @GET("list")
    suspend fun obtenerGeneros(
        @Query("api_key") apiKey: String
    ): retrofit2.Response<ResponseGeneros>
}