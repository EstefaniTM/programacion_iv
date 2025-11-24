package com.example.jetpackcompose.funeraria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class FuneralStatsScreen(val route: String) {
    object Home : FuneralStatsScreen("funeral_stats_home")
    object CostCalculator : FuneralStatsScreen("cost_calculator")
    object PaymentPlanner : FuneralStatsScreen("payment_planner")
    object ServiceCalculator : FuneralStatsScreen("service_calculator")
}

class FunerariaStats : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FuneralStatsNavApp() }
    }
}

@Composable
fun FuneralStatsNavApp() {
    val nav = rememberNavController()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = nav,
                startDestination = FuneralStatsScreen.Home.route
            ) {
                composable(FuneralStatsScreen.Home.route) { FuneralStatsHomeScreen(nav) }
                composable(FuneralStatsScreen.CostCalculator.route) { FuneralStatsCostCalculatorScreen(nav) }
                composable(FuneralStatsScreen.PaymentPlanner.route) { FuneralStatsPaymentPlannerScreen(nav) }
                composable(FuneralStatsScreen.ServiceCalculator.route) { FuneralStatsServiceCalculatorScreen(nav) }
            }
        }
    }
}

@Composable
fun FuneralStatsHomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Servicios Funerarios - Estadísticas",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { nav.navigate(FuneralStatsScreen.CostCalculator.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculadora de Costos")
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { nav.navigate(FuneralStatsScreen.PaymentPlanner.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Plan de Pagos")
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { nav.navigate(FuneralStatsScreen.ServiceCalculator.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculadora de Servicios")
        }
    }
}

@Composable
fun FuneralStatsCostCalculatorScreen(nav: NavController) {
    var casketCost by remember { mutableStateOf("") }
    var serviceCost by remember { mutableStateOf("") }
    var flowersCost by remember { mutableStateOf("") }

    val averageCost = funeralStatsAverageCost(casketCost, serviceCost, flowersCost)
    val message = when {
        averageCost == 0.0 -> "Ingrese costos válidos"
        averageCost >= 5000.0 -> "Servicio Premium"
        averageCost >= 3000.0 -> "Servicio Estándar"
        averageCost >= 1000.0 -> "Servicio Económico"
        else -> "Servicio Básico"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora de Costos",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
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

        Text("Costo Promedio = $${"%.2f".format(averageCost)}")
        Text(message)

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atrás")
        }
    }
}

@Composable
fun FuneralStatsPaymentPlannerScreen(nav: NavController) {
    var totalCost by remember { mutableStateOf("") }
    var monthlyPayment by remember { mutableStateOf("") }

    val months = funeralStatsPaymentMonths(totalCost, monthlyPayment)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Plan de Pagos",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = totalCost,
            onValueChange = { totalCost = it },
            label = { Text("Costo total del servicio ($)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = monthlyPayment,
            onValueChange = { monthlyPayment = it },
            label = { Text("Pago mensual ($)") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Meses necesarios = ${"%.1f".format(months)}")

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atrás")
        }
    }
}

@Composable
fun FuneralStatsServiceCalculatorScreen(nav: NavController) {
    var servicio1 by remember { mutableStateOf("") }
    var servicio2 by remember { mutableStateOf("") }

    val totalServicios = funeralStatsSum(servicio1, servicio2)
    val diferenciaServicios = funeralStatsRes(servicio1, servicio2)
    val multiplicacionServicios = funeralStatsMul(servicio1, servicio2)
    val divisionServicios = funeralStatsDiv(servicio1, servicio2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora de Servicios",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = servicio1,
            onValueChange = { servicio1 = it },
            label = { Text("Costo Servicio 1") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = servicio2,
            onValueChange = { servicio2 = it },
            label = { Text("Costo Servicio 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Total = ${servicio1} + ${servicio2} =  $${"%.2f".format(totalServicios)}")
        Text("Diferencia = ${servicio1} - ${servicio2} =  $${"%.2f".format(diferenciaServicios)}")
        Text("Multiplicación = ${servicio1} × ${servicio2} =  $${"%.2f".format(multiplicacionServicios)}")
        Text("División = ${servicio1} ÷ ${servicio2} =  $${"%.2f".format(divisionServicios)}")

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atrás")
        }
    }
}

fun funeralStatsAverageCost(c1: String, c2: String, c3: String): Double {
    val n1 = c1.replace(",", ".").toDoubleOrNull() ?: 0.0
    val n2 = c2.replace(",", ".").toDoubleOrNull() ?: 0.0
    val n3 = c3.replace(",", ".").toDoubleOrNull() ?: 0.0
    if (n1 == 0.0 && n2 == 0.0 && n3 == 0.0) return 0.0
    return (n1 + n2 + n3) / 3.0
}

fun funeralStatsPaymentMonths(total: String, monthly: String): Double {
    val t = total.replace(",", ".").toDoubleOrNull() ?: 0.0
    val m = monthly.replace(",", ".").toDoubleOrNull() ?: 0.0
    if (t <= 0.0 || m <= 0.0) return 0.0
    return t / m
}

fun funeralStatsSum(valor1: String, valor2: String): Double {
    val val1 = valor1.replace(",", ".").toDoubleOrNull() ?: 0.0
    val val2 = valor2.replace(",", ".").toDoubleOrNull() ?: 0.0
    return val1 + val2
}

fun funeralStatsRes(valor1: String, valor2: String): Double {
    val val1 = valor1.replace(",", ".").toDoubleOrNull() ?: 0.0
    val val2 = valor2.replace(",", ".").toDoubleOrNull() ?: 0.0
    return val1 - val2
}

fun funeralStatsMul(valor1: String, valor2: String): Double {
    val val1 = valor1.replace(",", ".").toDoubleOrNull() ?: 0.0
    val val2 = valor2.replace(",", ".").toDoubleOrNull() ?: 0.0
    return val1 * val2
}

fun funeralStatsDiv(valor1: String, valor2: String): Double {
    val val1 = valor1.replace(",", ".").toDoubleOrNull() ?: 0.0
    val val2 = valor2.replace(",", ".").toDoubleOrNull() ?: 0.0
    if (val2 == 0.0) return 0.0
    return val1 / val2
}

@Preview(showBackground = true)
@Composable
fun PreviewFuneralStatsNav() {
    FuneralStatsNavApp()
}