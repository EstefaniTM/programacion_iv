
package com.example.basics

fun main() {
    println("Ingrese su promedio")
    var promedio: Int = readln()?.toIntOrNull()?:0
    println("Tiene trabajo?")
    var trabaja: Boolean = readln()?.toBoolean() ?: false

    when{
        promedio >= 90 && trabaja == false->print("Beca completa")
        promedio >= 90 && trabaja == true->print("Beca parcia")
        promedio < 90->print("Sin beca")
    }

    println("Ingresa una hora 0-23")
    var hora : Int = readln()?.toIntOrNull()?:0
    when{
        hora == 7 && hora == 13->println("Clases en la mañana")
        hora == 14 && hora == 19->println("clases en la tarde")
        else->println("Horario lectivo")
    }
}


