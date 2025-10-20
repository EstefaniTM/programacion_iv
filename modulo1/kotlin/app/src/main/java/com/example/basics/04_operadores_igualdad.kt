package com.example.basics


fun main() {
    println("!!Operadores de igualdad!!") // El título original decía "aritméticos"

    // CASO 1: nombre1 y nombre2 apuntan al mismo objeto en el "String pool" de Kotlin.
    // Kotlin optimiza y reutiliza el mismo objeto para cadenas literales idénticas.
    val nombre1: String = "Yoda"
    val nombre2: String = "Yoda"

    // CASO 3: Creamos explícitamente un NUEVO objeto String a partir de un arreglo de caracteres.
    // Aunque el contenido es "Yoda", es una instancia diferente en memoria.
    val nombre3: String = String("Yoda".toCharArray()) // CORREGIDO el nombre del método

    println("--- Igualdad estructural (==) ---")
    println("Compara si el CONTENIDO es el mismo.")
    println("¿Es el contenido de nombre1 igual al de nombre2? ${nombre1 == nombre2}") // true, ambos contienen "Yoda"
    println("¿Es el contenido de nombre1 igual al de nombre3? ${nombre1 == nombre3}") // true, ambos contienen "Yoda"

    println("\n--- Igualdad referencial (===) ---")
    println("Compara si apuntan al MISMO OBJETO en memoria.")
    println("¿nombre1 y nombre2 son el mismo objeto? ${nombre1 === nombre2}") // true, Kotlin reutilizó el objeto
    println("¿nombre1 y nombre3 son el mismo objeto? ${nombre1 === nombre3}") // false, nombre3 es un objeto nuevo
}


