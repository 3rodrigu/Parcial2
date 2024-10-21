package com.example.parcial2.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial2.Model.Autores
import com.example.parcial2.Repository.AutoresRepository
import com.example.parcial2.Repository.PrestamosRepository
import com.example.parcial2.Screen.Form.AutorForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Pantalla para la gestión de prestamos
@Composable
fun GestionPrestamosScreen(
    prestamosRepository: PrestamosRepository,
    showAutorForm: Boolean,
    onShowFormChange: (Boolean) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gestión de Autores",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { onShowFormChange(true) }) {
            Text("Insertar Autor")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Función para mostrar autores */ }) {
            Text("Mostrar Autores")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Función para eliminar autor */ }) {
            Text("Eliminar Autor")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack) {
            Text("Volver")
        }

        // Mostrar el formulario de autor si está activo
        /* if (showAutorForm) {
             AutorForm(autoresRepository = autoresRepository) {
                 onShowFormChange(false)
             }*/
    }
}