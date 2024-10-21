package com.example.parcial2.Repository

import com.example.parcial2.DAO.PrestamosDao
import com.example.parcial2.Model.Relaciones.PrestamoDetalles
import com.example.parcial2.Model.Prestamos

class PrestamosRepository (private val prestamosDao: PrestamosDao){
    suspend fun insert(prestamos: Prestamos){
        prestamosDao.insert(prestamos)
    }
    suspend fun getAllprestamos(): List<PrestamoDetalles> {
        return prestamosDao.getAllprestamos()
    }
    suspend fun deleteById(prestamoId: Int): Int{
        return prestamosDao.deleteById(prestamoId)
    }
    suspend fun updateById(prestamoId: Int, libroId: Int, mienbroId: Int, fechaPrestamo: String, fechaDevolucion: String): Int {
        return prestamosDao.updateById(prestamoId, libroId, mienbroId, fechaPrestamo, fechaDevolucion )
    }

}