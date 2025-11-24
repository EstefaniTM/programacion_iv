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

sealed class FuneralMoneyScreen(val route: String) {
    object Home : FuneralMoneyScreen("funeral_money_home")
    object CostCalculator : FuneralMoneyScreen("cost_calculator")
    object ServicePlanner : FuneralMoneyScreen("service_planner")
}

class FunerariaMoney : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FuneralMoneyNavApp() }
    }
}

@Composable
fun FuneralMoneyNavApp() {
    val nav = rememberNavController()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = nav,
                startDestination = FuneralMoneyScreen.Home.route
            ) {
                composable(FuneralMoneyScreen.Home.route) { FuneralMoneyHomeScreen(nav) }
                composable(FuneralMoneyScreen.CostCalculator.route) { FuneralMoneyCostCalculatorScreen(nav) }
                composable(FuneralMoneyScreen.ServicePlanner.route) { FuneralMoneyServicePlannerScreen(nav) }
            }
        }
    }
}

@Composable
fun FuneralMoneyHomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Servicios Funerarios - Finanzas",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { nav.navigate(FuneralMoneyScreen.CostCalculator.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculadora de Costos")
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { nav.navigate(FuneralMoneyScreen.ServicePlanner.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Planificador de Servicios")
        }
    }
}

@Composable
fun FuneralMoneyCostCalculatorScreen(nav: NavController) {
    var casketCost by remember { mutableStateOf("") }
    var serviceCost by remember { mutableStateOf("") }
    var flowersCost by remember { mutableStateOf("") }

    val totalCost = calculateFuneralTotalCost(casketCost, serviceCost, flowersCost)
    val monthlyPayment = calculateFuneralMonthlyPayment(totalCost)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color(0xFFE8F5E8)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora de Costos",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        OutlinedTextField(
            value = casketCost,
            onValueChange = { casketCost = it },
            label = { Text("Costo del Ataúd") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = serviceCost,
            onValueChange = { serviceCost = it },
            label = { Text("Costo del Servicio") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = flowersCost,
            onValueChange = { flowersCost = it },
            label = { Text("Costo de Flores") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Costo Total = $${"%.2f".format(totalCost)}")
        Text("Pago Mensual (6 meses) = $${"%.2f".format(monthlyPayment)}")

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atrás")
        }
    }
}

@Composable
fun FuneralMoneyServicePlannerScreen(nav: NavController) {
    var serviceType by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Planificador de Servicios",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Tipo de Servicio", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = serviceType,
                    onValueChange = { serviceType = it },
                    label = { Text("Capilla, Iglesia, etc.") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Ubicación y Fecha", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Ubicación") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atrás")
        }
    }
}

// Cambiamos los nombres de las funciones para evitar conflictos
fun calculateFuneralTotalCost(casket: String, service: String, flowers: String): Double {
    val c = casket.replace(",", ".").toDoubleOrNull() ?: 0.0
    val s = service.replace(",", ".").toDoubleOrNull() ?: 0.0
    val f = flowers.replace(",", ".").toDoubleOrNull() ?: 0.0
    return c + s + f
}

fun calculateFuneralMonthlyPayment(totalCost: Double): Double {
    if (totalCost <= 0.0) return 0.0
    return totalCost / 6.0
}

@Preview(showBackground = true)
@Composable
fun PreviewFuneralMoneyNav() {
    FuneralMoneyNavApp()
}