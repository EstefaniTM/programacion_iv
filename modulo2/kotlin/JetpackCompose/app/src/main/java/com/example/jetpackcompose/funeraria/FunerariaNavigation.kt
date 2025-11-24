package com.example.jetpackcompose.funeraria

import android.os.Bundle
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

data class ServicioFunerarioNavigation(val id: Int, val nombreDifunto: String)

class FunerariaNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NavFunerariaApp() }
    }
}

@Composable
fun NavFunerariaApp() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            val nav = rememberNavController()
            NavHost(navController = nav, startDestination = "home") {

                // Pantalla lista de servicios
                composable("home") {
                    HomeFunerariaScreen(
                        onOpenDetail = { id, nombre ->
                            val safe = URLEncoder.encode(nombre, StandardCharsets.UTF_8.toString())
                            nav.navigate("detail/$id/$safe")
                        }
                    )
                }

                // Pantalla detalle con argumentos tipados
                composable(
                    route = "detail/{id}/{nombre}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.IntType },
                        navArgument("nombre") { type = NavType.StringType }
                    )
                ) { backStack ->
                    val id = backStack.arguments?.getInt("id") ?: -1
                    val nombre = backStack.arguments?.getString("nombre") ?: ""
                    DetailFunerariaScreen(
                        id = id,
                        nombreDifunto = nombre,
                        onBack = { nav.popBackStack() }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeFunerariaScreen(onOpenDetail: (Int, String) -> Unit) {
    val servicios = remember {
        listOf(
            ServicioFunerarioNavigation(1, "Juan Pérez García"),
            ServicioFunerarioNavigation(2, "María López Martínez"),
            ServicioFunerarioNavigation(3, "Carlos Rodríguez Silva"),
            ServicioFunerarioNavigation(4, "Ana Fernández Cruz")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Servicios Funerarios", style = MaterialTheme.typography.titleLarge)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(servicios, key = { it.id }) { servicio ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenDetail(servicio.id, servicio.nombreDifunto) }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Servicio #${servicio.id} — ${servicio.nombreDifunto}")
                    }
                }
            }
        }
    }
}

@Composable
fun DetailFunerariaScreen(id: Int, nombreDifunto: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Detalles del Servicio", style = MaterialTheme.typography.titleLarge)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Número de Servicio: $id", style = MaterialTheme.typography.titleMedium)
                Text("Nombre del Difunto: $nombreDifunto", style = MaterialTheme.typography.bodyLarge)

                // Información adicional del servicio
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tipo de Servicio: Capilla", style = MaterialTheme.typography.bodyMedium)
                Text("Fecha: 15/12/2024", style = MaterialTheme.typography.bodyMedium)
                Text("Estado: Programado", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Button(onClick = onBack) { Text("⬅️ Volver a la lista") }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeFuneraria() {
    MaterialTheme {
        HomeFunerariaScreen { _, _ -> }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailFuneraria() {
    MaterialTheme {
        DetailFunerariaScreen(
            id = 9,
            nombreDifunto = "Ejemplo Difunto",
            onBack = {}
        )
    }
}