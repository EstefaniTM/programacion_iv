package com.example.basics

fun main(){
    println("Listas")
    val inmutableLista: List<Int> = listOf(1, 2, 3)
    println("Lista Inmutable: ${inmutableLista}")

    val mutableLista: MutableList<Int> = mutableListOf(4, 5, 6)
    println("Lista Mutable (inicial): ${mutableLista}")

    mutableLista.add(7)
    println("Lista Mutable (después de añadir): ${mutableLista}")

    mutableLista.removeAt(0)
    println("Lista Mutable (después de remover el índice 0): ${mutableLista}")

    println("Valores finales en la lista mutable:")
    for(item in mutableLista) println(item)

    val  colores = mutableListOf("rojo", "verde")
    colores.add("azul")
    colores+="amarillo"
    colores.add(1,"Blanco")
    println(colores)
    colores.removeAt(0)
    println(colores)
    colores[0]="negro"
    println(colores)
    colores.clear()
    println(colores.isEmpty())

    println("Busquedas con mutablelist")
    val nombres = mutableListOf ("Juan", "Luis", "Pedro")
    println(nombres.find {it.startsWith("L")})
    println(nombres.firstNotNullOf { it.length>4 })
    println(nombres.any {it.contains('p')})
    println(nombres.none{it=="X"})

    println("Ordenamiento")
    val nombresDesordenados = mutableListOf(5,6,7,8,9,3,2,4)
    println(nombresDesordenados.sorted())
    println(nombresDesordenados.sortedDescending())
    println(nombresDesordenados.distinct())



}
