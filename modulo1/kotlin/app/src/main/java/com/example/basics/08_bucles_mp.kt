package com.example.myapplication


fun main(){
    println("bucles")
    val salasFuneral = listOf("B-11", "B-12", "B-13", "D-11", "D-12", "D-13")
    for ((index,salasFuneral) in salasFuneral.withIndex()){
        println("${index+1}.$salasFuneral")
    }

    for (tamaño in 100..200 step 25) {
        println("Ataúd de $tamaño cm - Precio: $${tamaño * 5}")
    }

    for (minuto in 10 downTo 1) {
        println("La ceremonia termina en $minuto minutos...")
    }
    println("La velación ha concluido. 🙏")

    for (salaFuneral in salasFuneral){
        if (salaFuneral=="D-11")continue
        if (salaFuneral=="D13")break
        println("Entrando a $salaFuneral")
    }
}