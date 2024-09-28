package com.example.tp_equipo6

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Query("select * from user_entity")
    fun getAll() : List<Usuario>

    @Insert
    fun insert(usuario: Usuario)
}