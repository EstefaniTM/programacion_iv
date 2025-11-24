package com.ute.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun AccountScreen(openSettings: () -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("Francisco Higuera")) }
    var email by remember { mutableStateOf(TextFieldValue("francisco@example.com")) }
    var bio by remember { mutableStateOf(TextFieldValue("Desarrollador y docente UTE.")) }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("👤 Mi cuenta", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(name, { name = it }, label = { Text("Nombre") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(email, { email = it }, label = { Text("Email") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(bio, { bio = it }, label = { Text("Bio") }, minLines = 3, modifier = Modifier.fillMaxWidth())

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = openSettings) { Text("Ajustes") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { /* guardar futuro (DataStore/Backend) */ }) { Text("Guardar") }
        }
    }
}