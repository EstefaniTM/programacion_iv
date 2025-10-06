package com.example.basics

import java.sql.DriverManager.println

fun main(){
    println("Interpolación de strings")
    val nombreFallesido: String = "Leonardo"
    val tallaAtaud: Int = 180
    val sexoFallecido: String = "Masculino"

    println("${nombreFallesido.uppercase()} del sexo ${sexoFallecido}")
    println("Se cambiara el tamaño del ataud a ${tallaAtaud+10} cm")

    println("String Multilinea")
    val contratador: String = "Maria"
    val tallaActual: Int = 190
    val mensaje: String = """
        Querida $contratador
        Este mensaje es para que sepa que se a cambiado 
        el tamaño del ataud del fallecido $nombreFallesido al tamaño
        de $tallaAtaud
    """
    println(mensaje)

    println("Conversiones")
    val edadFallecido: String = "25"
    val edadConvertida: Int = edadFallecido.toInt()
    println("edadConvertida")

    val numero: Double = 50.8
    val numeroConvertido: String = numero.toString()
    println(numeroConvertido)
}

