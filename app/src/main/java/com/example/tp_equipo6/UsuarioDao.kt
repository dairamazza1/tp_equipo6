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

    // Consulta por email
    @Query("SELECT * FROM user_entity WHERE email = :email")
    fun getUserByEmail(email: String): List<Usuario>
}