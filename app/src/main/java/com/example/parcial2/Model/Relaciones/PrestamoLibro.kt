package com.example.parcial2.Model.Relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.example.parcial2.Model.Libros
import com.example.parcial2.Model.Prestamos


data class PrestamoLibro(
    @Embedded val prestamos: Prestamos,
    @Relation(
        parentColumn = "libroId",
        entityColumn = "libroId"
    )
    val libros: List<Libros>
)