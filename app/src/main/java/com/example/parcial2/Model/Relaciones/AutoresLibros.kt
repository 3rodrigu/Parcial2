package com.example.parcial2.Model

import androidx.room.Embedded
import androidx.room.Relation


data class AutoresLibros(
    val titulo: String,
    val genero: String,
    val nombreAutor: String,
    val apellidoAutor: String
)