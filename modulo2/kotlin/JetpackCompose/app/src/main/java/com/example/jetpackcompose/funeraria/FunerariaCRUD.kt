package com.example.jetpackcompose.funeraria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Modelo simple para servicios funerarios
data class FuneralService(
    val id: Int,
    val clientName: String,
    val deceasedName: String,
    val serviceType: String
)

class FunerariaCRUD : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SimpleFunerariaScreen() }
    }
}

@Composable
fun SimpleFunerariaScreen() {
    // Estado local para los servicios
    var services by remember { mutableStateOf(emptyList<FuneralService>()) }

    // Estado para el formulario
    var clientName by remember { mutableStateOf("") }
    var deceasedName by remember { mutableStateOf("") }
    var serviceType by remember { mutableStateOf("Capilla") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            "Servicios Funerarios",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Formulario simple
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Agregar Servicio", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = clientName,
                    onValueChange = { clientName = it },
                    label = { Text("Nombre del Cliente") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = deceasedName,
                    onValueChange = { deceasedName = it },
                    label = { Text("Nombre del Difunto") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = serviceType,
                    onValueChange = { serviceType = it },
                    label = { Text("Tipo de Servicio") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (clientName.isNotBlank() && deceasedName.isNotBlank()) {
                            val newService = FuneralService(
                                id = services.size + 1,
                                clientName = clientName,
                                deceasedName = deceasedName,
                                serviceType = serviceType
                            )
                            services = services + newService
                            // Limpiar formulario
                            clientName = ""
                            deceasedName = ""
                            serviceType = "Capilla"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = clientName.isNotBlank() && deceasedName.isNotBlank()
                ) {
                    Text("Agregar Servicio")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de servicios
        Text(
            "Servicios Registrados: ${services.size}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (services.isEmpty()) {
            Text(
                "No hay servicios registrados",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(services) { service ->
                    ServiceItem(service = service) {
                        // Eliminar servicio
                        services = services.filter { it.id != service.id }
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceItem(service: FuneralService, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                "Servicio #${service.id}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Cliente: ${service.clientName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Difunto: ${service.deceasedName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Tipo: ${service.serviceType}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onDelete,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text("Eliminar Servicio")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSimpleFuneraria() {
    SimpleFunerariaScreen()
}