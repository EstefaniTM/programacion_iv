package com.example.basics

import java.sql.DriverManager.println

fun main(){
    println("!!Estructura de control!!")
    println("Condicionales")
    val pagoInicial = 50
    val costoTotal = 100
    if(pagoInicial<costoTotal){
        println("Proceso detenido por costo bajo")
    } else{
        println("Pago aceptado, proceso iniciado")
    }

    println("\nRango segun nivel de la fuerza") // Added \n for better formatting
    var tamanioAtaud: Int = 170
    if(tamanioAtaud>170){
        println("Se escogera un ataud mas grande")
    } else if (tamanioAtaud<170){
        println("se escoge un ataud mas pequenio")
    } else{
        println("Se escoge un ataud generico")
    }

    println("Elemento del zodiaco, di tu signo del zodiaco")
    var zodiaco: String = "Aries"
    when(zodiaco){
        "Aries","Leo","Sagitario"->print("Fuego")
        "Tauro","Virgo","Capicornio"->print("Tierra")
        "Geminis", "Libra", "Acuario"->print("Aire")
        "Cancer", "Escorpio", "Cancer"->print("Agua")
        else -> print("error")
    }

    println("Di que numero de la sala de velacion y te diremos " +
            "la direccion que se encuentra")
    var numeroSala: String = "B-10"
    when(numeroSala){
        "B-10", "B-12", "B-11"->println("Se encuentra en la secion derecha, piso 1")
        "D-10", "D-11", "D-12"->println("Se encuentra en el piso 2, a la izquierda")
        else -> println("Lugar desconocido, verifique si no escribio mal")
    }
}
