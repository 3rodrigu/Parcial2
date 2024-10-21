import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Import para manejar el tamaño del texto
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import com.example.parcial2.Model.Autores
import com.example.parcial2.Model.User
import com.example.parcial2.Repository.AutoresRepository
import com.example.parcial2.Repository.LibrosRepository
import com.example.parcial2.Repository.MiembrosRepository
import com.example.parcial2.Repository.PrestamosRepository
import com.example.parcial2.Repository.UserRepository
import com.example.parcial2.Screen.Form.AutorForm
import com.example.parcial2.Screen.GestionAutoresScreen
import com.example.parcial2.Screen.GestionLibrosScreen
import com.example.parcial2.Screen.GestionMiembrosScreen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class MenuOption {
    AUTORES, MIEMBROS, LIBROS, PRESTAMOS, NONE
}

@Composable
fun GestionApp(
    autoresRepository: AutoresRepository,
    prestamosRepository: PrestamosRepository,
    librosRepository: LibrosRepository,
    miembrosRepository: MiembrosRepository
) {
    var selectedMenuOption by remember { mutableStateOf(MenuOption.NONE) }
    var showAutorForm by remember { mutableStateOf(false) }
    var showMiembroForm by remember { mutableStateOf(false) }
    var showLibroForm by remember { mutableStateOf(false) }

    // Mostrar solo el contenido correspondiente a la opción seleccionada
    when (selectedMenuOption) {
        MenuOption.AUTORES -> {
            // Pantalla para gestionar autores
            GestionAutoresScreen(
                autoresRepository = autoresRepository,
                showAutorForm = showAutorForm,
                onShowFormChange = { showAutorForm = it },
                onBack = { selectedMenuOption = MenuOption.NONE }
            )
        }

        MenuOption.MIEMBROS -> {
            GestionMiembrosScreen(
                miembrosRepository = miembrosRepository,
                showMiembroForm = showMiembroForm,
                onShowFormChange = { showMiembroForm = it },
                onBack = { selectedMenuOption = MenuOption.NONE }
            )
        }

       MenuOption.LIBROS -> {
            GestionLibrosScreen(
                librosRepository = librosRepository,
                showLibroForm = showLibroForm,
                onShowFormChange = { showLibroForm = it },
                onBack = { selectedMenuOption = MenuOption.NONE}
            )
        }

      /*  MenuOption.PRESTAMOS -> {
            GestionPrestamosScreen(
                prestamosRepository = prestamosRepository,
                onBack = { selectedMenuOption = MenuOption.NONE }
            )
        }*/

        else -> {
            // Pantalla principal con botones de entidades
            MenuPrincipal(
                onSelectMenu = { selectedMenuOption = it }
            )
        }
    }
}

// Pantalla principal
@Composable
fun MenuPrincipal(onSelectMenu: (MenuOption) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Libreria",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { onSelectMenu(MenuOption.AUTORES) }) {
            Text("Autores")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onSelectMenu(MenuOption.MIEMBROS) }) {
            Text("Miembros")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onSelectMenu(MenuOption.LIBROS) }) {
            Text("Libros")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onSelectMenu(MenuOption.PRESTAMOS) }) {
            Text("Préstamos")
        }
    }
}


