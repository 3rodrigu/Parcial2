package com.example.parcial2.Model

import androidx.room.Embedded
import androidx.room.Relation


data class AutoresLibros(
    @Embedded val autor: Autores,
    @Relation(
        parentColumn = "autorId",
        entityColumn = "autorId"
    )
    val libros: List<Libros>
)