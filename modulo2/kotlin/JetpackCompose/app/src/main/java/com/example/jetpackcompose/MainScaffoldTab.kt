package com.example.jetpackcompose

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

sealed class Tab(val route: String, val label: String, val emoji: String) {
    data object Home : Tab("home", "Home", "🏠")
    data object Buscar : Tab("search", "Buscar", "🔎")
    data object Perfil : Tab("profile", "Perfil", "👤")
    companion object { val all = listOf(Home, Buscar, Perfil) }
}

class MainScaffoldTab : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppScaffoldTabs() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffoldTabs() {
    MaterialTheme {
        val nav = rememberNavController()
        val current by nav.currentBackStackEntryAsState()
        val currentRoute = current?.destination?.route ?: Tab.Home.route
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
                    title = { Text(titleForRoute(currentRoute)) },
                    actions = {
                        TextButton(onClick = { showSnackbar = "Acción 1" }) { Text("Acc 1") }
                        TextButton(onClick = { showSnackbar = "Acción 2" }) { Text("Acc 2") }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    Tab.all.forEach { tab ->
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
                startDestination = Tab.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Tab.Home.route) { HomeScreen() }
                composable(Tab.Buscar.route) { SearchScreen() }
                composable(Tab.Perfil.route) { ProfileScreen() }
            }
        }
    }
}

private fun titleForRoute(route: String): String = when (route) {
    Tab.Home.route -> "Inicio"
    Tab.Buscar.route -> "Buscar"
    Tab.Perfil.route -> "Perfil"
    else -> "App"
}

/** Pantallas simples para cada pestaña */
@Composable
fun HomeScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("🏠 Bienvenido a Home", style = MaterialTheme.typography.titleLarge)
            Text("Este es tu scaffold base para futuras apps con CRUD.")
        }
    }
}

@Composable
fun SearchScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("🔎 Buscar", style = MaterialTheme.typography.titleLarge)
            Text("Aquí podrás filtrar listados y consultar APIs.")
        }
    }
}

@Composable
fun ProfileScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("👤 Perfil", style = MaterialTheme.typography.titleLarge)
            Text("Zona de ajustes de usuario y sesión.")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScaffold() { AppScaffoldTabs() }