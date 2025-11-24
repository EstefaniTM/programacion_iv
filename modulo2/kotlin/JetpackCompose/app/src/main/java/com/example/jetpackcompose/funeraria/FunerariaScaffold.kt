package com.example.jetpackcompose.funeraria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

sealed class FuneralTab(val route: String, val label: String, val emoji: String) {
    data object Inicio : FuneralTab("inicio", "Inicio", "🏠")
    data object Servicios : FuneralTab("servicios", "Servicios", "⚰️")
    data object Contacto : FuneralTab("contacto", "Contacto", "📞")
    companion object { val all = listOf(Inicio, Servicios, Contacto) }
}

class FunerariaScaffold : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppFunerariaScaffold() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppFunerariaScaffold() {
    MaterialTheme {
        val nav = rememberNavController()
        val current by nav.currentBackStackEntryAsState()
        val currentRoute = current?.destination?.route ?: FuneralTab.Inicio.route
        val scope = rememberCoroutineScope()
        var showSnackbar by remember { mutableStateOf<String?>(null) }
        val snackbarHostState = remember { SnackbarHostState() }

        // Mostrar Snackbars simples desde acciones del TopAppBar
        LaunchedEffect(showSnackbar) {
            showSnackbar?.let {
                snackbarHostState.showSnackbar(it)
                showSnackbar = null
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(titleForFuneralRoute(currentRoute)) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    actions = {
                        TextButton(onClick = { showSnackbar = "📞 Llamando a urgencias..." }) {
                            Text("Urgencias")
                        }
                        TextButton(onClick = { showSnackbar = "📍 Abriendo ubicación..." }) {
                            Text("Ubicación")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    FuneralTab.all.forEach { tab ->
                        val selected = currentRoute == tab.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                nav.navigate(tab.route) {
                                    popUpTo(nav.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Text(tab.emoji) },
                            label = { Text(tab.label) }
                        )
                    }
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            NavHost(
                navController = nav,
                startDestination = FuneralTab.Inicio.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(FuneralTab.Inicio.route) { InicioFunerariaScreen() }
                composable(FuneralTab.Servicios.route) { ServiciosFunerariaScreen() }
                composable(FuneralTab.Contacto.route) { ContactoFunerariaScreen() }
            }
        }
    }
}

private fun titleForFuneralRoute(route: String): String = when (route) {
    FuneralTab.Inicio.route -> "Funeraria Paz Eterna"
    FuneralTab.Servicios.route -> "Nuestros Servicios"
    FuneralTab.Contacto.route -> "Contacto 24/7"
    else -> "Funeraria"
}

/** Pantallas para la funeraria */
@Composable
fun InicioFunerariaScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("🏠 Funeraria Paz Eterna", style = MaterialTheme.typography.titleLarge)
            Text("Servicios funerarios con respeto y dignidad", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth(0.8f)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📞 Urgencias 24/7", style = MaterialTheme.typography.titleMedium)
                    Text("+1-800-FUNERAL", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun ServiciosFunerariaScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("⚰️ Nuestros Servicios", style = MaterialTheme.typography.titleLarge)

            val servicios = listOf(
                "Servicio en Capilla",
                "Servicio Religioso",
                "Cremación",
                "Traslados",
                "Asesoría Legal"
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                servicios.forEach { servicio ->
                    Card(
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        Text(
                            text = "• $servicio",
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactoFunerariaScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("📞 Contacto", style = MaterialTheme.typography.titleLarge)
            Text("Estamos aquí para ayudarte", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth(0.8f)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("📞 Teléfono:", style = MaterialTheme.typography.titleMedium)
                    Text("+1-800-FUNERAL")

                    Text("📍 Dirección:", style = MaterialTheme.typography.titleMedium)
                    Text("Calle Principal #123")

                    Text("🕒 Horario:", style = MaterialTheme.typography.titleMedium)
                    Text("24 horas / 7 días")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFunerariaScaffold() {
    AppFunerariaScaffold()
}