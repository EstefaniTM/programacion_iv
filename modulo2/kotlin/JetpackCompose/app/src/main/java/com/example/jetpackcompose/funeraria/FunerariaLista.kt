package com.example.jetpackcompose.funeraria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ServicioFunerario(val id: Int, val nombre: String, val tipo: String)

class FunerariaLista : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ListaServiciosApp() }
    }
}

@Composable
fun ListaServiciosApp() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            ListaServiciosScreen()
        }
    }
}

@Composable
fun ListaServiciosScreen() {
    val servicios = remember {
        listOf(
            ServicioFunerario(1, "Juan Pérez", "Capilla"),
            ServicioFunerario(2, "María García", "Iglesia"),
            ServicioFunerario(3, "Carlos López", "Cremación"),
            ServicioFunerario(4, "Ana Martínez", "Domicilio")
        )
    }

    var seleccionado by remember { mutableStateOf<ServicioFunerario?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Servicios Programados", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(servicios) { servicio ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { seleccionado = servicio }
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(servicio.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(servicio.tipo, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (seleccionado != null) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text("Servicio Seleccionado:", style = MaterialTheme.typography.titleMedium)
                    Text("Difunto: ${seleccionado!!.nombre}")
                    Text("Tipo: ${seleccionado!!.tipo}")
                }
            }
        } else {
            Text("Selecciona un servicio para ver detalles")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLista() { ListaServiciosApp() }