package com.example.parcial2.Model.Relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.example.parcial2.Model.Miembros
import com.example.parcial2.Model.Prestamos


data class PrestamoMiembro(
    @Embedded val prestamos: Prestamos,
    @Relation(
        parentColumn = "mienbroId",
        entityColumn = "mienbroId"
    )
    val miembros: List<Miembros>
)