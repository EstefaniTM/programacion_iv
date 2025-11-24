package com.example.jetpackcompose.funeraria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.R

sealed class FunerariaScreen(val route: String) {
    object Home : FunerariaScreen("home_funeraria")
    object Bmi : FunerariaScreen("bmi")
    object Converter : FunerariaScreen("converter")
}

class MainFuneralServices : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                FunerariaNavApp()
            }
        }
    }
}

@Composable
fun FunerariaNavApp() {
    val nav = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = nav,
            startDestination = FunerariaScreen.Home.route
        ) {
            composable(FunerariaScreen.Home.route) { FunerariaHomeScreen(nav) }


            composable(FunerariaScreen.Bmi.route) { CalculadoraIMCScreen(nav) }

            composable(FunerariaScreen.Converter.route) { ConversorUnidadesScreen(nav) }

        }
    }
}

@Composable
fun FunerariaHomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ejercicios de Salud",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { nav.navigate(FunerariaScreen.Bmi.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculadora de IMC (BMI)")
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { nav.navigate(FunerariaScreen.Converter.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Conversor Metros / Kilómetros")
        }
    }
}



@Composable
fun CalculadoraIMCScreen(nav: NavController) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val bmi = bmiValue(weight, height)
    val bmiText = bmiCategory(bmi)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de IMC (BMI)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.health),
            contentDescription = "Imagen de salud",
            modifier = Modifier.size(120.dp)
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Altura (m)") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("IMC = ${"%.2f".format(bmi)}")
        Text(bmiText)

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atrás")
        }
    }
}

@Composable
fun ConversorUnidadesScreen(nav: NavController) {
    var meters by remember { mutableStateOf("") }
    var kilometers by remember { mutableStateOf("") }

    val metersToKm = metersToKilometers(meters)
    val kmToMeters = kilometersToMeters(kilometers)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Conversor Metros / Kilómetros",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("De metros a kilómetros", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = meters,
                    onValueChange = { meters = it },
                    label = { Text("Metros") }
                )
                Text("Resultado: ${"%.3f".format(metersToKm)} km")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("De kilómetros a metros", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = kilometers,
                    onValueChange = { kilometers = it },
                    label = { Text("Kilómetros") }
                )
                Text("Resultado: ${"%.3f".format(kmToMeters)} m")
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

fun bmiValue(weight: String, height: String): Double {
    val w = weight.replace(",", ".").toDoubleOrNull() ?: 0.0
    val h = height.replace(",", ".").toDoubleOrNull() ?: 0.0
    if (w <= 0.0 || h <= 0.0) return 0.0
    return w / (h * h)
}

fun bmiCategory(bmi: Double): String {
    if (bmi == 0.0) return "Introduce valores válidos"
    return when {
        bmi < 18.5 -> "Bajo peso"
        bmi < 25.0 -> "Peso normal"
        bmi < 30.0 -> "Sobrepeso"
        else -> "Obesidad"
    }
}

fun metersToKilometers(meters: String): Double {
    val m = meters.replace(",", ".").toDoubleOrNull() ?: 0.0
    return m / 1000.0
}

fun kilometersToMeters(kilometers: String): Double {
    val k = kilometers.replace(",", ".").toDoubleOrNull() ?: 0.0
    return k * 1000.0
}

@Preview(showBackground = true)
@Composable
fun PreviewFunerariaNav() {
    JetpackComposeTheme {
        FunerariaNavApp()
    }
}
