package com.example.parcial2.Model

import androidx.room.Embedded
import androidx.room.Relation


data class PrestamoMiembro(
    @Embedded val prestamos: Prestamos,
    @Relation(
        parentColumn = "mienbroId",
        entityColumn = "mienbroId"
    )
    val miembros: List<Miembros>
)