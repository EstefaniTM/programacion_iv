package com.example.jetpackcompose

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

sealed class Screen(val route: String) {
    object Home : Screen("Inicio")
    object Colors : Screen("Colores")
    object Math : Screen("Matemática")
}

class MainAreaTriangulo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BasicNavApp() }
    }
}

@Composable
fun BasicNavApp() {
    val nav = rememberNavController()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = nav, startDestination = Screen.Home.route) {
                composable(route = Screen.Home.route) { HomeScreen(nav) }
                composable(route = Screen.Colors.route) { ColorScreen(nav) }
                composable(route = Screen.Math.route) { MathScreen(nav) }
            }
        }
    }
}

@Composable
fun HomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ejemplos de Navegación", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { nav.navigate(Screen.Colors.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cambiar Color de Fondo")
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { nav.navigate(Screen.Math.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicios Básicos de Matemáticas")
        }
    }
}

@Composable
fun ColorScreen(nav: NavController) {
    var bg by remember { mutableStateOf(Color(0xFF101828)) }
    var current by remember { mutableStateOf("Dark blue") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Color de Pantalla", color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text("Color Actual: $current", color = Color.White)
        }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { bg = Color(0xFF0A84FF); current = "Azul" }) { Text("Azul") }
                Button(onClick = { bg = Color(0xFFFF0000); current = "Rojo" }) { Text("Rojo") }
                Button(onClick = { bg = Color(0xFFFFFF00); current = "Amarillo" }) { Text("Amarillo") }
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = { nav.navigateUp() }, modifier = Modifier.fillMaxWidth()) {
                Text("Atras")
            }
        }
    }
}

@Composable
fun MathScreen(nav: NavController) {
    var base by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("") }
    var radio by remember { mutableStateOf("") }

    val area = triangleArea(base, height)
    val (finalPrice, saved) = discountCalc(price, discount)
    val areacir = circleArea(radio)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Ejercicios", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Área del Circulo", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = base, onValueChange = { base = it }, label = { Text("Base") })
                OutlinedTextField(value = height, onValueChange = { height = it }, label = { Text("Altura") })
                Text("Area = ${"%.2f".format(area)}")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Discount", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
                OutlinedTextField(value = discount, onValueChange = { discount = it }, label = { Text("Discount %") })
                Text("Final price = ${"%.2f".format(finalPrice)}")
                Text("Saved = ${"%.2f".format(saved)}")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Área de un circulo", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(value = radio, onValueChange = { radio = it }, label = { Text("Radio") })
                Text("Area: ${areacir}")
            }
        }

        Button(onClick = { nav.navigateUp() }, modifier = Modifier.fillMaxWidth()) {
            Text("Atras")
        }
    }
}

fun triangleArea(base: String, height: String): Double {
    val b = base.replace(",", ".").toDoubleOrNull() ?: 0.0
    val h = height.replace(",", ".").toDoubleOrNull() ?: 0.0
    return (b * h) / 2.0
}

fun discountCalc(price: String, discount: String): Pair<Double, Double> {
    val p = price.replace(",", ".").toDoubleOrNull() ?: 0.0
    val d = discount.replace(",", ".").toDoubleOrNull() ?: 0.0
    val saved = p * (d / 100)
    val final = p - saved
    return final to saved
}

fun circleArea(radio: String): Double {
    val area = (radio.toDouble() * radio.toDouble()) * Math.PI
    return area
}

@Preview(showBackground = true)
@Composable
fun PreviewNav() {
    BasicNavApp()
}