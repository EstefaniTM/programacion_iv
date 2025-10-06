package com.example.basics

import java.sql.DriverManager.println

fun main(){
    println("!Operadores logicos!!")

    val porcentajeIva: Double= 15.0
    val precioSinIva: Double= 100.50
    println("El iva es ${precioSinIva * (porcentajeIva / 100)}")
}