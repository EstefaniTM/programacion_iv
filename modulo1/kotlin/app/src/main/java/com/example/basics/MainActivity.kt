package com.example.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear un TextView para mostrar resultados
        val textView = TextView(this).apply {
            text = "Ejecuta tus ejercicios desde el menú o botones"
            textSize = 18f
            setPadding(50, 50, 50, 50)
        }

        setContentView(textView)

        // Ejecutar un ejercicio automáticamente al iniciar
        ejecutarEjercicioVariables()
    }

    private fun ejecutarEjercicioVariables() {
        // Llama a tu código de 01_variables_mp.kt
        val resultado = StringBuilder()
        resultado.appendLine("=== Ejecutando Ejercicios ===")

        // Aquí puedes llamar funciones de tus otros archivos
        // Por ejemplo, si tienes una función en 01_variables_mp.kt:
        // resultado.appendLine(ejecutarLogicaVariables())

        // Mientras tanto, ejecuta lógica directa:
        val nombre = "Ana"
        val edad = 25
        resultado.appendLine("Nombre: $nombre, Edad: $edad")

        // Mostrar en Logcat (pestaña "Logcat" en Android Studio)
        println(resultado.toString())

        // Actualizar el TextView
        findViewById<TextView>(android.R.id.text1)?.text = resultado.toString()
    }
}