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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class FuneralUtilitiesScreen(val route: String) {
    object Home : FuneralUtilitiesScreen("funeral_utilities_home")
    object ServiceTime : FuneralUtilitiesScreen("service_time")
    object CostCalculator : FuneralUtilitiesScreen("cost_calculator")
    object ServicePlanner : FuneralUtilitiesScreen("service_planner")
}

class FunerariaTravel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FuneralUtilitiesNavApp() }
    }
}

@Composable
fun FuneralUtilitiesNavApp() {
    val nav = rememberNavController()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = nav,
                startDestination = FuneralUtilitiesScreen.Home.route
            ) {
                composable(FuneralUtilitiesScreen.Home.route) { FuneralHomeScreen(nav) }
                composable(FuneralUtilitiesScreen.ServiceTime.route) { ServiceTimeScreen(nav) }
                composable(FuneralUtilitiesScreen.CostCalculator.route) { FuneralCostScreen(nav) }
                composable(FuneralUtilitiesScreen.ServicePlanner.route) { ServicePlannerScreen(nav) }
            }
        }
    }
}

@Composable
fun FuneralHomeScreen2 (nav: NavHostController) {
    val bg = Color(0xFF020617)
    val cardColor = Color(0xFF0F172A)
    val accent = Color(0xFF6366F1)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .widthIn(max = 480.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Utilidades Funerarias",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = accent
                )
                Text(
                    text = "Seleccione una utilidad:",
                    color = Color(0xFFE5E7EB)
                )
                Button(
                    onClick = { nav.navigate(FuneralUtilitiesScreen.ServiceTime.route) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = accent)
                ) {
                    Text("Duración del Servicio", color = Color.White)
                }
                Button(
                    onClick = { nav.navigate(FuneralUtilitiesScreen.CostCalculator.route) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22C55E))
                ) {
                    Text("Calculadora de Costos", color = Color.White)
                }

                Button(
                    onClick = { nav.navigate(FuneralUtilitiesScreen.ServicePlanner.route) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6))
                ) {
                    Text("Planificador de Servicios", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ServiceTimeScreen(nav: NavController) {
    val bg = Color(0xFF020617)
    val cardColor = Color(0xFF111827)
    val accent = Color(0xFF8B5CF6)

    var serviceType by rememberSaveable { mutableStateOf("") }
    var attendees by rememberSaveable { mutableStateOf("") }

    val estimatedTime = calculateServiceTime(serviceType, attendees)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Duración del Servicio",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = accent
                    )
                    OutlinedTextField(
                        value = serviceType,
                        onValueChange = { serviceType = it },
                        label = { Text("Tipo de Servicio") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Capilla, Iglesia, Cremación...") }
                    )
                    OutlinedTextField(
                        value = attendees,
                        onValueChange = { attendees = it },
                        label = { Text("Número de Asistentes") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Tiempo estimado = ${"%.1f".format(estimatedTime)} horas",
                        color = Color(0xFFE5E7EB)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { nav.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = accent)
            ) {
                Text("Atrás", color = Color.White)
            }
        }
    }
}

@Composable
fun FuneralCostScreen(nav: NavController) {
    val bg = Color(0xFF020617)
    val cardColor = Color(0xFF0F172A)
    val accent = Color(0xFFF97316)

    var serviceType by rememberSaveable { mutableStateOf("") }
    var casketType by rememberSaveable { mutableStateOf("") }
    var flowersBudget by rememberSaveable { mutableStateOf("") }

    val (baseCost, totalCost) = calculateFuneralCost(serviceType, casketType, flowersBudget)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Calculadora de Costos",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = accent
                    )
                    OutlinedTextField(
                        value = serviceType,
                        onValueChange = { serviceType = it },
                        label = { Text("Tipo de Servicio") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Capilla, Iglesia, Cremación...") }
                    )
                    OutlinedTextField(
                        value = casketType,
                        onValueChange = { casketType = it },
                        label = { Text("Tipo de Ataúd") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Económico, Estándar, Premium...") }
                    )
                    OutlinedTextField(
                        value = flowersBudget,
                        onValueChange = { flowersBudget = it },
                        label = { Text("Presupuesto para Flores ($)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Costo base = ${"%.2f".format(baseCost)} $",
                        color = Color(0xFFE5E7EB)
                    )
                    Text(
                        text = "Costo total = ${"%.2f".format(totalCost)} $",
                        color = Color(0xFFE5E7EB)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { nav.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = accent)
            ) {
                Text("Atrás", color = Color.White)
            }
        }
    }
}

@Composable
fun ServicePlannerScreen(nav: NavController) {
    val bg = Color(0xFF020617)
    val cardColor = Color(0xFF111827)
    val accent = Color(0xFF10B981)

    var clientName by rememberSaveable { mutableStateOf("") }
    var serviceYears by rememberSaveable { mutableStateOf("") }

    val servicePackage = calculateServicePackage(serviceYears)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Planificador de Servicios",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = accent
                    )
                    OutlinedTextField(
                        value = clientName,
                        onValueChange = { clientName = it },
                        label = { Text("Nombre del Cliente") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = serviceYears,
                        onValueChange = { serviceYears = it },
                        label = { Text("Años como Cliente") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "El cliente $clientName tiene derecho al: $servicePackage",
                        color = Color(0xFFE5E7EB)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { nav.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = accent)
            ) {
                Text("Atrás", color = Color.White)
            }
        }
    }
}

fun calculateServiceTime(serviceType: String, attendees: String): Double {
    val attendeesCount = attendees.toIntOrNull() ?: 0
    val baseTime = when (serviceType.lowercase()) {
        "capilla" -> 2.0
        "iglesia" -> 1.5
        "cremación" -> 1.0
        "domicilio" -> 3.0
        else -> 2.0
    }

    // Añadir tiempo extra por asistente (5 minutos por cada 10 personas)
    val extraTime = (attendeesCount / 10) * 5.0 / 60.0
    return baseTime + extraTime
}

fun calculateFuneralCost(serviceType: String, casketType: String, flowersBudget: String): Pair<Double, Double> {
    val flowers = flowersBudget.replace(",", ".").toDoubleOrNull() ?: 0.0

    val serviceCost = when (serviceType.lowercase()) {
        "capilla" -> 1500.0
        "iglesia" -> 2000.0
        "cremación" -> 1000.0
        "domicilio" -> 2500.0
        else -> 1500.0
    }

    val casketCost = when (casketType.lowercase()) {
        "económico" -> 800.0
        "estándar" -> 1500.0
        "premium" -> 3000.0
        else -> 1000.0
    }

    val baseCost = serviceCost + casketCost
    val totalCost = baseCost + flowers

    return baseCost to totalCost
}

fun calculateServicePackage(serviceYears: String): String {
    val years = serviceYears.toIntOrNull() ?: 0
    return when {
        years > 0 && years <= 5 -> "Paquete Básico (10% descuento)"
        years > 5 && years < 15 -> "Paquete Estándar (20% descuento)"
        years >= 15 -> "Paquete Premium (30% descuento)"
        else -> "Paquete Inicial (sin descuento)"
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFuneralUtilitiesNav() {
    FuneralUtilitiesNavApp()
}