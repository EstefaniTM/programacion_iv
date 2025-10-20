package com.example.basics

fun saludarFuneraria() {
    println("Hola esta es la Funeraria Luiza guerra")
}fun sumarTresNumeros(a: Int, b: Int, c: Int): Int {
    return a + b + c
}

fun cuadradoFuneraria(numero: Int) = numero * numero

fun retornoMultipleFuneraria(a: Int, b: Int, c: Int): Triple<Int, Int, Int> {
    val suma = a + b + c
    val resta = a - b - c
    val multiplicacion = a * b * c
    return Triple(suma, resta, multiplicacion)
}

fun main() {
    saludarFuneraria()

    val resultadoSuma = sumarTresNumeros(a = 10, b = 20, c = 5)
    println("El resultado de la suma de tres números es: $resultadoSuma")

    val resultadoCuadrado = cuadradoFuneraria(numero = 7)
    println("El cuadrado (versión funeraria) de 7 es: $resultadoCuadrado")

    val (suma, resta, multiplicacion) = retornoMultipleFuneraria(a = 5, b = 3, c = 4)
    println("Del retorno múltiple (Triple):")
    println("  - La suma es: $suma")
    println("  - La resta es: $resta")
    println("  - La multiplicación es: $multiplicacion")
}