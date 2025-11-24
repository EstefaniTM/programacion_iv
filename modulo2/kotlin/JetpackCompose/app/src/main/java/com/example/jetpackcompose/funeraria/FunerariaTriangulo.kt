package com.example.jetpackcompose.funeraria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class FuneralScreen(val route: String) {
    object Home : FuneralScreen("Inicio")
    object ServicePlanning : FuneralScreen("Planificación")
    object CostCalculator : FuneralScreen("Calculadora")
}

class FunerariaTriangulo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FuneralNavApp() }
    }
}

@Composable
fun FuneralNavApp() {
    val nav = rememberNavController()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = nav, startDestination = FuneralScreen.Home.route) {
                composable(route = FuneralScreen.Home.route) { FuneralHomeScreen(nav) }
                composable(route = FuneralScreen.ServicePlanning.route) { ServicePlanningScreen(nav) }
                composable(route = FuneralScreen.CostCalculator.route) { CostCalculatorScreen(nav) }
            }
        }
    }
}

@Composable
fun FuneralHomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Servicios Funerarios",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32)
        )
        Spacer(Modifier.height(30.dp))
        Button(
            onClick = { nav.navigate(FuneralScreen.ServicePlanning.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text("Planificar Servicio")
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { nav.navigate(FuneralScreen.CostCalculator.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D4037))
        ) {
            Text("Calculadora de Costos")
        }
        Spacer(Modifier.height(20.dp))
        Text(
            "24/7 - Servicio de Urgencias",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun ServicePlanningScreen(nav: NavController) {
    var selectedCasket by remember { mutableStateOf("Económico") }
    var selectedFlowers by remember { mutableStateOf("Básico") }
    var selectedService by remember { mutableStateOf("Capilla") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Planificación del Servicio",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32)
        )

        // Selección de Ataúd
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Tipo de Ataúd", fontWeight = FontWeight.SemiBold)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Económico", "Standard", "Premium").forEach { casket ->
                        FilterChip(
                            selected = selectedCasket == casket,
                            onClick = { selectedCasket = casket },
                            label = { Text(casket) }
                        )
                    }
                }
            }
        }

        // Selección de Arreglos Florales
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Arreglos Florales", fontWeight = FontWeight.SemiBold)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Básico", "Standard", "Completo").forEach { flowers ->
                        FilterChip(
                            selected = selectedFlowers == flowers,
                            onClick = { selectedFlowers = flowers },
                            label = { Text(flowers) }
                        )
                    }
                }
            }
        }

        // Tipo de Servicio
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Tipo de Servicio", fontWeight = FontWeight.SemiBold)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Capilla", "Iglesia", "Domicilio").forEach { service ->
                        FilterChip(
                            selected = selectedService == service,
                            onClick = { selectedService = service },
                            label = { Text(service) }
                        )
                    }
                }
            }
        }

        // Resumen
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Resumen de Selección:", fontWeight = FontWeight.Bold)
                Text("• Ataúd: $selectedCasket")
                Text("• Flores: $selectedFlowers")
                Text("• Servicio: $selectedService")
            }
        }

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text("Guardar Planificación")
        }
    }
}

@Composable
fun CostCalculatorScreen(nav: NavController) {
    var casketCost by remember { mutableStateOf("") }
    var serviceCost by remember { mutableStateOf("") }
    var additionalCost by remember { mutableStateOf("") }

    val totalCost = calculateTotalCost(casketCost, serviceCost, additionalCost)
    val paymentPlan = calculatePaymentPlan(totalCost)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Calculadora de Costos",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32)
        )

        // Costo del Ataúd
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Costo del Ataúd", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = casketCost,
                    onValueChange = { casketCost = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Costo del Servicio
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Costo del Servicio", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = serviceCost,
                    onValueChange = { serviceCost = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Costos Adicionales
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Costos Adicionales", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = additionalCost,
                    onValueChange = { additionalCost = it },
                    label = { Text("Flores, traslado, etc.") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Resultados
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Resumen Financiero:", fontWeight = FontWeight.Bold)
                Text("Costo Total: $${"%.2f".format(totalCost)}", fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Text("Plan de Pagos (6 meses):", fontWeight = FontWeight.Medium)
                Text("Cuota Mensual: $${"%.2f".format(paymentPlan)}")
            }
        }

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text("Calcular")
        }
    }
}

fun calculateTotalCost(casket: String, service: String, additional: String): Double {
    val casketCost = casket.replace(",", ".").toDoubleOrNull() ?: 0.0
    val serviceCost = service.replace(",", ".").toDoubleOrNull() ?: 0.0
    val additionalCost = additional.replace(",", ".").toDoubleOrNull() ?: 0.0
    return casketCost + serviceCost + additionalCost
}

fun calculatePaymentPlan(totalCost: Double): Double {
    if (totalCost <= 0.0) return 0.0
    return totalCost / 6.0 // 6 meses
}

@Preview(showBackground = true)
@Composable
fun PreviewFuneralNav() {
    FuneralNavApp()
}