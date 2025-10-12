package com.example.basics

import android.R

fun main(){
    println("Mapas")

    val empleadoFuneral = listOf(
        mapOf(
            "nombre" to "Josefina",
            "Trabajo" to "Embalsamador",
            "telefono" to "0980890657"),
        mapOf(
            "nombre" to "Manuel",
            "Trabajo" to "Tanatopractor",
            "telefono" to "098777907"),
    )

    println("Datos de los empleados ${empleadoFuneral}")

    println("Mapa Mutable")

    val misionesCompletadas = mutableMapOf<String, Int>()
    misionesCompletadas ["Luke"] = 15
    misionesCompletadas ["Leia"] = 12
    misionesCompletadas.put("Han", 20)

    println("Misiones: ${misionesCompletadas}")

    val clienteFuneral = mutableMapOf<String, String>()
    clienteFuneral["Nombre"] = "Juan"
    clienteFuneral["Apellido"] = "Perez"
    clienteFuneral["Servicio"] = "Velorio"
    clienteFuneral.put("Nombre", "Maria")
    println("Datos del cliente ${clienteFuneral}")


    for ((index, empleado) in empleadoFuneral.withIndex()){
        println("Datos del empleado ${index + 1}")

        for ((clave, valor)in empleado){
            println("Su $clave es: $valor")
        }
    }


    val serviciosFuneraria = setOf("Traslado del cuerpo", "Preparacion del cuerpo", "Velorio")
    println("Tipos de servicios $serviciosFuneraria")

    val  recursosFuneraria = setOf("Ataud", "Velas", "sillas")
    println("Recursos de la funeraria son: $recursosFuneraria")

    val interseccion = serviciosFuneraria intersect recursosFuneraria
    println("Interseccion: $interseccion")

    val union = serviciosFuneraria union recursosFuneraria
    println("Union: $union")

    val diferencia = serviciosFuneraria - recursosFuneraria
    println("Diferencia: $diferencia")

}