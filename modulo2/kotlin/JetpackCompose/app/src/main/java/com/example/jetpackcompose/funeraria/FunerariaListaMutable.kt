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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ServicioFunerarioEditable(val id: Int, val nombreDifunto: String, val tipoServicio: String)

class FunerariaListaMutable : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ListaServiciosEditableApp() }
    }
}

@Composable
fun ListaServiciosEditableApp() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) { ListaServiciosEditableScreen() }
    }
}

@Composable
fun ListaServiciosEditableScreen() {
    // Estado de la lista
    var autoId by rememberSaveable { mutableStateOf(4) }
    val servicios = remember {
        mutableStateListOf(
            ServicioFunerarioEditable(1, "Juan Pérez", "Capilla"),
            ServicioFunerarioEditable(2, "María García", "Iglesia"),
            ServicioFunerarioEditable(3, "Carlos López", "Cremación"),
            ServicioFunerarioEditable(4, "Ana Martínez", "Domicilio")
        )
    }

    // Estado del formulario
    var nombreDifunto by rememberSaveable { mutableStateOf("") }
    var tipoServicio by rememberSaveable { mutableStateOf("") }
    var seleccionado by remember { mutableStateOf<ServicioFunerarioEditable?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Servicios Funerarios", style = MaterialTheme.typography.titleLarge)

        // --- Formulario agregar ---
        OutlinedTextField(
            value = nombreDifunto,
            onValueChange = { nombreDifunto = it },
            label = { Text("Nombre del Difunto") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = tipoServicio,
            onValueChange = { tipoServicio = it },
            label = { Text("Tipo de Servicio") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = { nombreDifunto = ""; tipoServicio = "" }) { Text("Limpiar") }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    val nombre = nombreDifunto.trim()
                    val tipo = tipoServicio.trim()
                    if (nombre.isNotEmpty() && tipo.isNotEmpty()) {
                        autoId += 1
                        servicios.add(ServicioFunerarioEditable(autoId, nombre, tipo))
                        nombreDifunto = ""; tipoServicio = ""
                    }
                },
                enabled = nombreDifunto.isNotBlank() && tipoServicio.isNotBlank()
            ) { Text("Agregar Servicio") }
        }

        Divider()

        // --- Lista ---
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(servicios, key = { it.id }) { servicio ->
                Card(Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { seleccionado = servicio }
                        ) {
                            Text(servicio.nombreDifunto, style = MaterialTheme.typography.titleMedium)
                            Text(servicio.tipoServicio, style = MaterialTheme.typography.bodyMedium)
                        }
                        TextButton(onClick = {
                            servicios.removeIf { it.id == servicio.id }
                            if (seleccionado?.id == servicio.id) seleccionado = null
                        }) { Text("Eliminar") }
                    }
                }
            }
        }

        // --- Detalle seleccionado ---
        if (seleccionado != null) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text("Servicio Seleccionado:", style = MaterialTheme.typography.titleMedium)
                    Text("ID: ${seleccionado!!.id}")
                    Text("Difunto: ${seleccionado!!.nombreDifunto}")
                    Text("Tipo: ${seleccionado!!.tipoServicio}")
                }
            }
        } else {
            Text("Selecciona un servicio para ver detalles.")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEditable() { ListaServiciosEditableApp() }