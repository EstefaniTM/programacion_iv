package com.example.basics

data class Jedi(val nombre: String, val edad: Int, val nivelFuerza: Int, val maestro: String?=null ){
    val rango: String get() = when {
        nivelFuerza>= 90 -> "Maestro"
        nivelFuerza>= 70 -> "Caballero"
        nivelFuerza>= 50 -> "Padawan"
        else -> {"Iniciado"}
    }

    fun puedeEnsenar(): Boolean = nivelFuerza >= 70

    fun entrenar(): Boolean = edad >= 5
}

fun main(){
    val luke = Jedi("Anakin Skywalker", 25, 75, "Obiwan Kenobi")
    println(luke)

    val (nombre,edad,nivelFuerza) = luke
    println("Nombre del jedi $nombre, edad $edad, nivel de fuerza $nivelFuerza")
    val ashoka = luke.copy(nombre="ashoka", nivelFuerza=80)
    println(ashoka)

    println("Rango de ashoka ${ashoka.rango}")

    println("Ashoka puede ensenar ${ashoka.puedeEnsenar()}")
}