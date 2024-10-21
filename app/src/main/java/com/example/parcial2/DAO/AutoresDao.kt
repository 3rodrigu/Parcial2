package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial2.Model.Autores


@Dao
interface AutoresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(autores: Autores): Long

    @Query("SELECT * FROM autores")
    suspend fun getAllAutores(): List<Autores>

    @Query("DELETE FROM autores WHERE autorId = :autorId")
    suspend fun deleteById(autorId: Int):Int

    @Query("UPDATE autores SET nombre = :nombre, apellido = :apellido, nacionalidad = :nacionalidad WHERE autorId = :autorId")
    suspend fun updateById(autorId: Int, nombre: String, apellido: String, nacionalidad: String): Int
}