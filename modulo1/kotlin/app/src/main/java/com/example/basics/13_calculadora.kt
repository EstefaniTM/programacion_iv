package com.example.basics

import kotlin.math.PI

fun sumaFuncion( a: Int,b: Int): Int{
    return a + b
}

fun restaFuncion( a: Int,b: Int): Int{
    return a - b
}

fun multiplicacionFuncion( a: Int,b: Int): Int{
    return a * b
}

fun divisionFuncion( a: Int,b: Int): Int{
    return a / b
}

fun main(){
    println("Ingresa un numero")
    var a: Int = readln()?.toIntOrNull()?:0
    println("Ingresa otro numero")
    var b: Int = readln()?.toIntOrNull()?:0

    println(restaFuncion(a,b))
    println(sumaFuncion(a,b))
    println(divisionFuncion(a,b))
    println(multiplicacionFuncion(a,b))
}