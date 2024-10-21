package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.parcial2.Model.Relaciones.PrestamoDetalles
import com.example.parcial2.Model.Prestamos


@Dao
interface PrestamosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamos: Prestamos): Long

    @Transaction
    @Query("""
        SELECT libros.titulo AS tituloLibro, miembros.nombre AS nombreMiembro, miembros.apellido AS apellidoMiembro,
               prestamos.fechaPrestamo, prestamos.fechaDevolucion
        FROM prestamos
        INNER JOIN libros ON prestamos.libroId = libros.libroId
        INNER JOIN miembros ON prestamos.mienbroId = miembros.mienbroId
    """)
    fun getAllprestamos(): List<PrestamoDetalles>

    @Query("DELETE FROM prestamos WHERE prestamoId = :prestamoId")
    suspend fun deleteById(prestamoId: Int):Int

    @Query("UPDATE prestamos SET libroId = :libroId, mienbroId = :mienbroId, fechaPrestamo = :fechaPrestamo, fechaDevolucion = :fechaDevolucion  WHERE prestamoId = :prestamoId")
    suspend fun updateById(prestamoId: Int, libroId: Int, mienbroId: Int, fechaPrestamo: String, fechaDevolucion: String): Int
}