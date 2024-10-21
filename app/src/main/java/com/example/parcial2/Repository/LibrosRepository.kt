package com.example.parcial2.Repository

import com.example.parcial2.DAO.LibrosDao
import com.example.parcial2.Model.Relaciones.AutoresLibros
import com.example.parcial2.Model.Libros

class LibrosRepository (private val librosDao: LibrosDao){
    suspend fun insert(libros: Libros){
        librosDao.insert(libros)
    }
    suspend fun getAllLibros(): List<Libros> {
        return librosDao.getAllLibros()
    }
    suspend fun deleteById(libroId: Int): Int{
        return librosDao.deleteById(libroId)
    }
    suspend fun updateById(libroId: Int, titulo: String, genero: String, autorId: Int): Int {
        return librosDao.updateById(libroId, titulo, genero, autorId)
    }

}