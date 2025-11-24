package com.ute.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ute.app.ui.nav.NavRoute

@Composable
fun HomeScreen(
    goTo: (String) -> Unit,
    goToDetail: (Int) -> Unit
) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("🏠 Pantalla de inicio", style = MaterialTheme.typography.titleLarge)
        Text("Navega a las demás secciones:")

        Button(onClick = { goTo(NavRoute.Posts.route) }) { Text("📝 Posts (API)") }
        Button(onClick = { goTo(NavRoute.Account.route) }) { Text("👤 Mi Cuenta") }
        Button(onClick = { goTo(NavRoute.Settings.route) }) { Text("⚙️ Ajustes") }
        Button(onClick = { goTo(NavRoute.Gallery.route) }) { Text("🖼️ Galería de imágenes") }
        Button(onClick = { goTo(NavRoute.Video.route) }) { Text("🎬 Reproductor de video") }

        Spacer(Modifier.height(12.dp))
        Text("Atajo a un detalle (post #5):")
        OutlinedButton(onClick = { goToDetail(5) }) { Text("Abrir detalle ID=5") }
    }
}