package com.example.jetpackcompose.funeraria
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class FunerariaCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FichaServicioFunerarioApp() }
    }
}

@Composable
fun FichaServicioFunerarioApp() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) { FichaServicioFunerarioScreen() }
    }
}

@Composable
fun FichaServicioFunerarioScreen() {
    var nombreDifunto by rememberSaveable { mutableStateOf("") }
    var servicioUrgente by rememberSaveable { mutableStateOf(false) }
    var tipoServicio by rememberSaveable { mutableStateOf("Capilla") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Entrada de datos del difunto
        OutlinedTextField(
            value = nombreDifunto,
            onValueChange = { nombreDifunto = it },
            label = { Text("Nombre del Difunto") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Selector de tipo de servicio
        OutlinedTextField(
            value = tipoServicio,
            onValueChange = { tipoServicio = it },
            label = { Text("Tipo de Servicio") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Capilla, Iglesia, Cremación, etc.") }
        )

        // Switch para servicio urgente
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Servicio Urgente")
            Switch(
                checked = servicioUrgente,
                onCheckedChange = { servicioUrgente = it }
            )
        }

        // Tarjeta con la ficha del servicio
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (servicioUrgente) Color(0xFFFFEBEE) else Color(0xFFE8F5E8)
            )
        ) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Información del servicio
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                if (servicioUrgente) Color(0xFFD32F2F)
                                else Color(0xFF2E7D32)
                            )
                    )
                    Column {
                        Text(
                            text = if (nombreDifunto.isBlank()) "Nombre del Difunto" else nombreDifunto,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = if (servicioUrgente) "🚨 Servicio Urgente" else "✅ Servicio Programado",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (servicioUrgente) Color(0xFFD32F2F) else Color(0xFF2E7D32)
                        )
                        Text(
                            text = "Tipo: $tipoServicio",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                // Información adicional según el tipo de servicio
                if (tipoServicio.isNotBlank()) {
                    Text(
                        text = when (tipoServicio.toLowerCase()) {
                            "capilla" -> "Servicio en nuestra capilla principal"
                            "iglesia" -> "Servicio religioso en iglesia"
                            "cremación" -> "Servicio de cremación incluido"
                            "domicilio" -> "Servicio en domicilio particular"
                            else -> "Servicio personalizado"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                // Acciones
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            nombreDifunto = ""
                            servicioUrgente = false
                            tipoServicio = "Capilla"
                        }
                    ) {
                        Text("Limpiar")
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(
                        onClick = { /* Guardar información del servicio */ },
                        enabled = nombreDifunto.isNotBlank() && tipoServicio.isNotBlank()
                    ) {
                        Text("Programar Servicio")
                    }
                }
            }
        }

        // Información adicional
        if (servicioUrgente) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        "📞 Servicio de Urgencias Activado",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFF57C00)
                    )
                    Text(
                        "Nuestro equipo se contactará inmediatamente",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFF57C00)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFichaServicio() {
    FichaServicioFunerarioApp()
}