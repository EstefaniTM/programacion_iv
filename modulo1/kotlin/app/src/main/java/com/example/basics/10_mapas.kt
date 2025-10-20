package com.example.basics

fun main(){
    println("Mapas")

    val fuerzaJedis = mapOf(
        "Luke" to 85,
        "Leia" to 80,
        "Obi-Wan" to 95,
        "Yoda" to 100
    )

    println("Fuerza de los Jedis: ${fuerzaJedis}")

    println("Mapa Mutable")

    val misionesCompletadas = mutableMapOf<String, Int>()
    misionesCompletadas ["Luke"] = 15
    misionesCompletadas ["Leia"] = 12
    misionesCompletadas.put("Han", 20)

    println("Misiones: ${misionesCompletadas}")

    for((jedi, fuerza) in fuerzaJedis){
        println("$jedi tiene nivel de fuerza $fuerza")
    }

    val planetasVisitados = setOf("Tatoonie", "Coruscant", "Dagobah")
    println("Planeras visitados: $planetasVisitados")

    val planetasPeligrosos = setOf("Mustafar", "Coruscant", "Korriban")
    println("Planetas Peligrosos: $planetasPeligrosos")

    println("Operaciones de conjuntos")
    val interseccion = planetasPeligrosos intersect planetasVisitados
    println("Interseccion: $interseccion")

    val union = planetasPeligrosos union planetasVisitados
    println("Union: $union")

    val diferencia = planetasPeligrosos - planetasVisitados
    println("Diferencia: $diferencia")

    println("Ingresa una hora 0-23")

}