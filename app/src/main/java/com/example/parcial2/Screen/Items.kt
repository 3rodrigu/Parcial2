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
import com.example.parcial2.Model.User
import com.example.parcial2.Repository.UserRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable

fun UserApp(userRepository: UserRepository) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var selectedUserId by remember { mutableStateOf<Int?>(null) }
    var deleteUserId by remember { mutableStateOf("") }
    var users by remember { mutableStateOf(listOf<User>()) }
    var mostrarUsuarios by remember { mutableStateOf(false) } // Estado para controlar la visibilidad de los usuarios

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Función para cargar usuarios
    fun cargarUsuarios() {
        scope.launch {
            users = withContext(Dispatchers.IO) {
                userRepository.getAllUsers()
            }
        }
    }

    // Función para validar los campos de entrada
    fun validarCampos(): Boolean {
        if (nombre.isBlank()) {
            Toast.makeText(context, "El campo 'Nombre' no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }
        if (apellido.isBlank()) {
            Toast.makeText(context, "El campo 'Apellido' no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }
        if (edad.isBlank() || edad.toIntOrNull() == null) {
            Toast.makeText(context, "El campo 'Edad' no puede estar vacío y debe ser un número", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Agregar un espacio al inicio del formulario
            Spacer(modifier = Modifier.height(32.dp))

            // Título de "Registro de Usuario"
            Text(
                text = "Registro de Usuario",
                fontSize = 24.sp, // Tamaño de fuente ajustable
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Formulario de Registro
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text(text = "Apellido") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text(text = "Edad") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para registrar o modificar
            Button(onClick = {
                if (validarCampos()) {
                    val user = User(
                        id = selectedUserId ?: 0, // Usamos el ID existente si se está modificando, o 0 si es un nuevo usuario
                        nombre = nombre,
                        apellido = apellido,
                        edad = edad.toIntOrNull() ?: 0
                    )

                    scope.launch {
                        withContext(Dispatchers.IO) {
                            if (selectedUserId != null) {
                                // Si `selectedUserId` no es null, estamos en modo de modificación
                                userRepository.updateById(user.id, user.nombre, user.apellido, user.edad)
                            } else {
                                // Si `selectedUserId` es null, estamos en modo de nuevo registro
                                userRepository.insert(user)
                            }
                        }
                        Toast.makeText(
                            context,
                            if (selectedUserId != null) "Usuario modificado" else "Usuario registrado",
                            Toast.LENGTH_SHORT
                        ).show()
                        cargarUsuarios() // Actualizar lista después de registrar o modificar
                        selectedUserId = null // Reiniciar `selectedUserId` después de la operación
                        nombre = ""
                        apellido = ""
                        edad = ""
                    }
                }
            }) {
                Text(text = if (selectedUserId != null) "Modificar Usuario" else "Registrar Usuario")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para ingresar el ID del usuario a eliminar
            TextField(
                value = deleteUserId,
                onValueChange = { deleteUserId = it },
                label = { Text(text = "ID del Usuario para eliminar") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para mostrar u ocultar la lista de usuarios
            Button(onClick = {
                if (!mostrarUsuarios) {
                    cargarUsuarios() // Cargar usuarios solo cuando se vaya a mostrar la lista
                }
                mostrarUsuarios = !mostrarUsuarios // Cambiar el estado de visibilidad
            }) {
                Text(text = if (mostrarUsuarios) "Ocultar Usuarios" else "Mostrar Usuarios")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de usuarios registrados (se muestra solo si `mostrarUsuarios` es true)
            if (mostrarUsuarios) {
                LazyColumn {
                    items(users) { user ->
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text("ID: ${user.id}, ${user.nombre} ${user.apellido}, Edad: ${user.edad}")
                            Spacer(modifier = Modifier.height(4.dp))

                            Button(onClick = {
                                scope.launch {
                                    val rowsDeleted = withContext(Dispatchers.IO) {
                                        userRepository.deleteById(user.id)
                                    }
                                    if (rowsDeleted > 0) {
                                        Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                                        cargarUsuarios() // Actualizar la lista de usuarios después de eliminar uno
                                    } else {
                                        Toast.makeText(context, "Error al eliminar usuario", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }) {
                                Text(text = "Eliminar")
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Botón para seleccionar un usuario para modificación
                            Button(onClick = {
                                nombre = user.nombre
                                apellido = user.apellido
                                edad = user.edad.toString()
                                selectedUserId = user.id
                            }) {
                                Text(text = "Modificar")
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}
