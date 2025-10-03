package com.example.basics

import java.sql.DriverManager.println

fun main(){
    println("!Operadores aritmeticos!!")
    val nombre1: String = "Yoda"
    val nombre2: String = "Yoda"
    val nombre3: String = String("Yoda".toCharArrray())
    println("Igualdad estructural (contenido)")
    println(nombre1==nombre2)
    println(nombre1==nombre3)
    println("Igualdad Referencial (misma instancia)")
    println(nombre1===nombre2)
    println(nombre1===nombre3)
    println("igualdad Referencial (misma instancia)")
}

