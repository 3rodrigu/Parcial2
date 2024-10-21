package com.example.parcial2.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "libros",
    foreignKeys = [
        ForeignKey(
            entity = Autores::class,
            parentColumns = ["autorId"],
            childColumns = ["autorId"],
            onDelete = ForeignKey.RESTRICT //Impedirá la eliminación del Autor si existen libros asociados
        )
    ],
    indices = [Index(value = ["autorId"])]
)
data class Libros(
    @PrimaryKey(autoGenerate = true)
    val libroId: Int = 0,
    val titulo: String,
    val genero: String,
    val autorId: Int

)