package com.ute.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var dark by remember { mutableStateOf(true) }
    var notifications by remember { mutableStateOf(true) }
    var analytics by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("⚙️ Ajustes", style = MaterialTheme.typography.titleLarge)

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Tema oscuro"); Switch(dark, onCheckedChange = { dark = it })
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Notificaciones"); Switch(notifications, onCheckedChange = { notifications = it })
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Analytics"); Switch(analytics, onCheckedChange = { analytics = it })
        }

        Divider()
        Text("* En un proyecto real, persiste en DataStore/Preferences.")
    }
}