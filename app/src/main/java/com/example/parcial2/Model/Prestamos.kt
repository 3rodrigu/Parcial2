package com.example.parcial2.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "prestamos",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = Miembros::class,
            parentColumns = ["mienbroId"],
            childColumns = ["mienbroId"],
            onDelete = ForeignKey.RESTRICT //Impedirá la eliminación del Miembro si existen Prestamos asociados
        ),
        androidx.room.ForeignKey(
            entity = Libros::class,
            parentColumns = ["libroId"],
            childColumns = ["libroId"],
            onDelete = ForeignKey.RESTRICT  //Impedirá la eliminación del Libro si existen Prestamos asociados
        )
    ],
    indices = [Index(value = ["mienbroId"]), Index(value = ["libroId"])]
)
data class Prestamos(
    @PrimaryKey(autoGenerate = true)
    val prestamoId: Int = 0,
    val libroId: Int,
    val mienbroId: Int,
    val fechaPrestamo: String,
    val fechaDevolucion: String
)