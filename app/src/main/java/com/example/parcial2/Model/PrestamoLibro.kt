package com.example.parcial2.Model

import androidx.room.Embedded
import androidx.room.Relation


data class PrestamoLibro(
    @Embedded val prestamos: Prestamos,
    @Relation(
        parentColumn = "libroId",
        entityColumn = "libroId"
    )
    val libros: List<Libros>
)