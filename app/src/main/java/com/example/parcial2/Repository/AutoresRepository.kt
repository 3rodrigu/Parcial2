package com.example.parcial2.Repository

import com.example.parcial2.DAO.AutoresDao
import com.example.parcial2.Model.Autores

class AutoresRepository (private val autoresDao: AutoresDao){
    suspend fun insert(autores: Autores){
        autoresDao.insert(autores)
    }
    suspend fun getAllAutores(): List<Autores> {
        return autoresDao.getAllAutores()
    }
    suspend fun deleteById(libroId: Int): Int{
        return autoresDao.deleteById(libroId)
    }
    suspend fun updateById(autorId: Int, nombre: String, apellido: String, nacionalidad: String): Int {
        return autoresDao.updateById(autorId, nombre, apellido, nacionalidad)
    }

}