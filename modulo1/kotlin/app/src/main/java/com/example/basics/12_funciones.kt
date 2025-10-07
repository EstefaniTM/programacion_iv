package com.example.basics

fun saludar(){
    println("Hola desde una funcion de kotlin")
}

// funcion con parametros y retorno
fun sumar(a: Int, b: Int): Int{
    return a + b
}

//funcion con expresion single-expresion funcion
fun cuadrado(numero:Int) = numero * numero

// funcion con retorno multiple
fun retornoMultiple(a: Int, b: Int): Pair<Int, Int> {
    val suma = a+b
    val resta = a-b
    return Pair(suma,resta)

}

// Funcion lamdba

fun main(){
    saludar()
    val resultado = sumar(a = 6, b = 8)
    println(resultado)

    println(cuadrado(8))
    println(retornoMultiple(a = 18, b = 5))

    // Funcion lambda
    val cuadradoLambda = {x: Int-> x*x}
    val saludoLambda = {nombre: String-> ", $nombre"}
    println(cuadradoLambda(5))
    println(saludoLambda("Juan Luis Guerra"))

}
