package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.parcial2.Model.Libros


@Dao
interface LibrosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libros: Libros): Long

    @Transaction
    @Query("SELECT l.libroId, l.titulo, l.genero FROM libros l")
    fun getAllLibros(): List<Libros>

    @Query("DELETE FROM libros WHERE libroId = :libroId")
    suspend fun deleteById(libroId: Int):Int

    @Query("UPDATE libros SET titulo = :titulo, genero = :genero, autorId = :autorId WHERE libroId = :libroId")
    suspend fun updateById( libroId: Int, titulo: String, genero: String, autorId: Int ): Int
}