package com.example.basics

fun main(){
    println("Listas")
    val inmutableLista: List<String> = listOf("ataud","flores","velorios")
    println("Lista Inmutable: ${inmutableLista}")


    val mutableLista: MutableList<String> = mutableListOf("crematorio", "velatorio", "embalzamiento")
    println("Lista Mutable (inicial): ${mutableLista}")
    mutableLista.add("preparacion del cuerpo")
    println("Lista Mutable (después de añadir): ${mutableLista}")

    mutableLista.removeAt(0)
    println("Lista Mutable (después de remover el índice 0): ${mutableLista}")

    println("Valores finales en la lista mutable:")
    for(item in mutableLista) println(item)

    val  funeraria = mutableListOf("velorio", "trasladacion")
    funeraria.add("Traslado")
    funeraria+="Asesoria legal"
    funeraria.add(2,"Flores")
    println(funeraria)
    funeraria.removeAt(0)
    println(funeraria)
    funeraria[0]="Ataud"
    println(funeraria)
    funeraria.clear()
    println(funeraria.isEmpty())

    println("Busquedas con mutablelist")
    val objetos = mutableListOf("ataus", "flores", "velas")
    println(objetos.find{it.startsWith("a")})
    println(objetos.firstNotNullOf { it.length>4 })
    println(objetos.any{it.contains('f')})
    println(objetos.none{it=="v"})

    println("Ordenamiento")
    val nombresDesordenados = mutableListOf(5,6,7,8,9,3,2,4, 10,30,32)
    println(nombresDesordenados.sorted())
    println(nombresDesordenados.sortedDescending())
    println(nombresDesordenados.distinct())



}
