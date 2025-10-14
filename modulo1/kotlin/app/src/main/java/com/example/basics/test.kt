package com.example.basics

fun main() {
    var energia = 100
    print("Número de etapas: ")
    val etapas = readln().toInt()

    for (etapa in 1..etapas) {
        val terreno = (1..3).random()

        when (terreno) {
            1 -> energia -= 5
            2 -> energia -= 10
            3 -> energia -= 15
        }

        if (energia <= 0) {
            println("Abandona en etapa $etapa")
            return
        }
    }

    println("Rally completado con energía $energia")
}