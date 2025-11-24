package com.example.jetpackcompose.funeraria
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class FunerariaBoton : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppEstadoServicio() }
    }
}

@Composable
fun AppEstadoServicio() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            EstadoServicioScreen()
        }
    }
}

@Composable
fun EstadoServicioScreen() {
    var servicioActivo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Estado del servicio de urgencias
        Text(
            if (servicioActivo) "🚨 Servicio de Urgencias ACTIVO" else "💤 Servicio de Urgencias EN PAUSA",
            style = MaterialTheme.typography.titleMedium
        )

        // Información adicional según el estado
        Text(
            if (servicioActivo) "Disponibles 24/7 para atención inmediata"
            else "Servicio regular - Horario de oficina",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        // Botón con colores apropiados para el contexto funerario
        Button(
            onClick = { servicioActivo = !servicioActivo },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (servicioActivo) Color(0xFFD32F2F) else Color(0xFF2E7D32)
            )
        ) {
            Text(
                if (servicioActivo) "Pausar Servicio" else "Activar Servicio de Urgencias",
                color = Color.White
            )
        }

        // Información de contacto adicional
        if (servicioActivo) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📞 Línea de Emergencia", fontWeight = FontWeight.Bold)
                    Text("+1-800-FUNERAL")
                    Text("Disponible las 24 horas")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EstadoPreview() {
    AppEstadoServicio()
}