package com.example.basics

fun saludarDesdeFuncion() {
    println("Hola desde una funcion de kotlin")
}

fun sumarEnEsteContexto(a: Int, b: Int): Int {return a + b
}

fun cuadrado(numero: Int) = numero * numero

fun retornoMultiple(a: Int, b: Int): Pair<Int, Int> {
    val suma = a + b
    val resta = a - b
    return Pair(suma, resta)
}

fun main() {
    saludarDesdeFuncion()

    val resultado = sumarEnEsteContexto(a = 6, b = 8)
    println("El resultado de la suma es: $resultado")

    println("El cuadrado de 8 es: ${cuadrado(8)}")

    val (resultadoSuma, resultadoResta) = retornoMultiple(a = 18, b = 5)
    println("Del retorno múltiple, la suma es: $resultadoSuma y la resta es: $resultadoResta")

    println("--- Demostración de Lambdas ---")

    val cuadradoLambda = { x: Int -> x * x }
    println("El resultado de la lambda 'cuadrado' con 5 es: ${cuadradoLambda(5)}")

    val saludoLambda = { nombre: String -> "Hola desde una lambda, $nombre" }
    println(saludoLambda("Juan Luis Guerra"))
}
