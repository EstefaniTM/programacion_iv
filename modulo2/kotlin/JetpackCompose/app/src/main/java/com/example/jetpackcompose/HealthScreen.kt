package com.example.jetpackcompose

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

sealed class HealthScreen(val route: String) {
    object Home : HealthScreen("health_home")
    object Bmi : HealthScreen("bmi")
    object Converter : HealthScreen("converter")
}

class MainHealthExercises : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HealthNavApp() }
    }
}

@Composable
fun HealthNavApp() {
    val nav = rememberNavController()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = nav,
                startDestination = HealthScreen.Home.route
            ) {
                composable(HealthScreen.Home.route) { HealthHomeScreen(nav) }
                composable(HealthScreen.Bmi.route) { BmiScreen(nav) }
                composable(HealthScreen.Converter.route) { ConverterScreen(nav) }
            }
        }
    }
}

@Composable
fun HealthHomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Health Exercises",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { nav.navigate(HealthScreen.Bmi.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BMI Calculator")
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { nav.navigate(HealthScreen.Converter.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Meters / Kilometers Converter")
        }
    }
}

@Composable
fun BmiScreen(nav: NavController) {
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
            text = "BMI Calculator",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.health),
            contentDescription = "Health image",
            modifier = Modifier.size(120.dp)
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (m)") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("BMI = ${"%.2f".format(bmi)}")
        Text(bmiText)

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

@Composable
fun ConverterScreen(nav: NavController) {
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
            text = "Meters / Kilometers Converter",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("From meters to kilometers", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = meters,
                    onValueChange = { meters = it },
                    label = { Text("Meters") }
                )
                Text("Result: ${"%.3f".format(metersToKm)} km")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("From kilometers to meters", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = kilometers,
                    onValueChange = { kilometers = it },
                    label = { Text("Kilometers") }
                )
                Text("Result: ${"%.3f".format(kmToMeters)} m")
            }
        }

        Button(
            onClick = { nav.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
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
    if (bmi == 0.0) return "Enter valid values"
    return when {
        bmi < 18.5 -> "Underweight"
        bmi < 25.0 -> "Normal weight"
        bmi < 30.0 -> "Overweight"
        else -> "Obesity"
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
fun PreviewHealthNav() {
    HealthNavApp()
}