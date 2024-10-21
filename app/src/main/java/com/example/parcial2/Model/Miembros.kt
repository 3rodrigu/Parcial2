package com.example.parcial2.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "miembros")
data class Miembros(
    @PrimaryKey(autoGenerate = true)
    val mienbroId: Int = 0,
    val nombre: String,
    val apellido: String,
    val fechaInscripcion: String

)