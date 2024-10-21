package com.example.parcial2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.parcial2.DAO.AutoresDao
import com.example.parcial2.DAO.LibrosDao
import com.example.parcial2.DAO.MiembrosDao
import com.example.parcial2.DAO.PrestamosDao
import com.example.parcial2.Model.Autores
import com.example.parcial2.Model.Libros
import com.example.parcial2.Model.Miembros
import com.example.parcial2.Model.Prestamos


@Database(entities = [Autores::class, Libros::class, Miembros::class, Prestamos::class], version = 1, exportSchema = false)
abstract class LibreriaDatabase : RoomDatabase()  {
    abstract fun LibrosDao(): LibrosDao
    abstract fun AutoresDao(): AutoresDao
    abstract fun MiembrosDao(): MiembrosDao
    abstract fun PrestamosDao(): PrestamosDao

    companion object{
        @Volatile
        private var INSTANCE: LibreriaDatabase? = null

        fun getDatabase(context: Context):LibreriaDatabase{
            return INSTANCE ?: synchronized( this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibreriaDatabase::class.java,
                    "libreria-database"
                )
                    .addCallback(object : Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            db.execSQL("PRAGMA foreign_keys=ON;") // Habilitar claves foráneas
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}