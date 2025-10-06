package com.example.basics

fun main(){
    println("Tabla de multiplicar")
    println("Ingrese el valor")
    var valor: Int = readln()?.toIntOrNull()?:0

    for(i in 1..12 step 1){
        println("${valor} x $i = ${i*valor}")
    }
}