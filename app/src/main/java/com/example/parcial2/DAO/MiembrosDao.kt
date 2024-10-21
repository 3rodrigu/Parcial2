package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial2.Model.Miembros


@Dao
interface MiembrosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(miembros: Miembros): Long

    @Query("SELECT * FROM miembros")
    suspend fun getAllMiembros(): List<Miembros>

    @Query("DELETE FROM miembros WHERE mienbroId = :mienbroId")
    suspend fun deleteById(mienbroId: Int):Int

    @Query("UPDATE miembros SET nombre = :nombre, apellido = :apellido, fechaInscripcion = :fechaInscripcion WHERE mienbroId = :mienbroId ")
    suspend fun updateById(mienbroId: Int, nombre: String, apellido: String, fechaInscripcion: String): Int
}