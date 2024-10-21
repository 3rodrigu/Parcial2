package com.example.parcial2
import GestionApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.parcial2.DAO.AutoresDao
import com.example.parcial2.DAO.LibrosDao
import com.example.parcial2.DAO.MiembrosDao
import com.example.parcial2.DAO.PrestamosDao
import com.example.parcial2.Database.LibreriaDatabase
import com.example.parcial2.Repository.AutoresRepository
import com.example.parcial2.Repository.LibrosRepository
import com.example.parcial2.Repository.MiembrosRepository
import com.example.parcial2.Repository.PrestamosRepository


class MainActivity : ComponentActivity() {

    private lateinit var AutoresDao: AutoresDao
    private lateinit var AutoresRepository: AutoresRepository
    private lateinit var librosDao: LibrosDao
    private lateinit var librosRepository: LibrosRepository
    private lateinit var miembrosDao: MiembrosDao
    private lateinit var miembrosRepository: MiembrosRepository
    private lateinit var prestamosDao: PrestamosDao
    private lateinit var prestamosRepository: PrestamosRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = LibreriaDatabase.getDatabase(applicationContext)

        // Inicializar DAOs
        AutoresDao = db.AutoresDao()
        librosDao = db.LibrosDao()
        miembrosDao = db.MiembrosDao()
        prestamosDao = db.PrestamosDao()

        // Inicializar repositorios
        AutoresRepository = AutoresRepository(AutoresDao)
        librosRepository = LibrosRepository(librosDao)
        miembrosRepository = MiembrosRepository(miembrosDao)
        prestamosRepository = PrestamosRepository(prestamosDao)

        enableEdgeToEdge()

        // Pasar los repositorios a la UI
        setContent{

            GestionApp(
                autoresRepository = AutoresRepository,
                prestamosRepository = prestamosRepository,
                librosRepository = librosRepository,
                miembrosRepository = miembrosRepository
            )
        }
    }
}