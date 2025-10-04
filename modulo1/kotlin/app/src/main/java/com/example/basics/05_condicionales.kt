package com.example.basics

import java.sql.DriverManager.println

fun main(){
    println("!!Estructura de control!!")
    println("Condicionales")
    val value1 = 10
    val value2 = 15
    if(value1>value2){
        println("El mayor es ${value1}")
    } else{
        println("El mayor es ${value2}")
    }

    println("\nRango segun nivel de la fuerza") // Added \n for better formatting
    var fuerza: Int = 10
    if(fuerza > 10){
        println("Maestro")
    } else if (fuerza > 5){
        println("Caballero Jedi")
    } else{
        println("Padawan")
    }

    println() // Added for spacing
    var tipoDroide: String = "BB-8"
    when(tipoDroide){
        "R2-D2","R2-Q5"->println("Droide astromecanico") // Changed for accuracy
        "C-3PO","C-3PA"->println("Droide de protocolo")
        "BB-8","BB-9E"->print("Droide de nueva generacion")
        else -> print("Modelo desconocido")
    }

    println() // Added for spacing
    var peligro: Int = 10
    var recompensa: Int = 50 // 2. FIXED: Added the missing colon (:)
    when{
        peligro > 8 && recompensa < 1000 -> print("Mision Rechazada")
        peligro <= 3 -> print("Mision Aceptada")
        else -> print("Requiere Evaluacion adicional")
    }

    println() // Added for spacing
    var semaforo: String = "Rojo"
    when(semaforo){
        "Rojo"->print("Alto")
        "Verde"->print("Pasa") // Capitalized 'Verde' for consistency if needed
        "Amarillo"->print("Precaucion") // Changed for clarity
        else -> print("Color no encontrado")
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

}
