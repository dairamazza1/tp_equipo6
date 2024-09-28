package com.example.tp_equipo6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class Usuario(
    @ColumnInfo (name = "name") var userName : String,
    @ColumnInfo (name = "lastName") var userLastName : String,
    @ColumnInfo (name = "genre") var userGenre : String,
    @ColumnInfo (name = "birthday") var userBirthday : String,
    @ColumnInfo (name = "email") var userEmail : String,
    @ColumnInfo (name = "password") var userPassword: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
