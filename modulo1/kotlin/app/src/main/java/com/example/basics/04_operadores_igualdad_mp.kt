package com.example.basics


fun main() {
    println("!!Operadores de igualdad!!") // El título original decía "aritméticos"

    val nombre1: String = "Julia Sandobal"
    val nombre2: String = "julia snadobal"

    val nombre3: String = String("julia snadobal".toCharArray())

    println("Compara si el CONTENIDO es el mismo.")
    println("¿Es el contenido de nombre1 igual al de nombre2? ${nombre1 == nombre2}") // true, ambos contienen "Yoda"
    println("¿Es el contenido de nombre1 igual al de nombre3? ${nombre1 == nombre3}")

    println("\n--- Igualdad referencial (===) ---")
    println("Compara si apuntan al MISMO OBJETO en memoria.")
    println("¿nombre1 y nombre2 son el mismo objeto? ${nombre1 === nombre2}")
    println("¿nombre1 y nombre3 son el mismo objeto? ${nombre1 === nombre3}")
}
