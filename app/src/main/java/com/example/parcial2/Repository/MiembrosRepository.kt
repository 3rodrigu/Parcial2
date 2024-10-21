package com.example.parcial2.Repository

import com.example.parcial2.DAO.MiembrosDao
import com.example.parcial2.Model.Miembros

class MiembrosRepository (private val miembrosDao: MiembrosDao){
    suspend fun insert(miembros: Miembros){
        miembrosDao.insert(miembros)
    }
    suspend fun getAllMiembros(): List<Miembros> {
        return miembrosDao.getAllMiembros()
    }
    suspend fun deleteById(mienbroId: Int): Int{
        return miembrosDao.deleteById(mienbroId)
    }
    suspend fun updateById(mienbroId: Int, nombre: String, apellido: String, fechaInscripcion: String): Int {
        return miembrosDao.updateById(mienbroId, nombre, apellido, fechaInscripcion)
    }

}