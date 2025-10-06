package com.example.myapplication

import java.sql.DriverManager.println

fun main(){
    println("bucles")
    val salasFuneral = listOf("B-11", "B-12", "B-13", "D-11", "D-12", "D-13")
    for ((index,salasFuneral) in salasFuneral.withIndex()){
        println("${index+1}.$salasFuneral")
    }
    // rangos de paso
    for (i in 0 .. 20 step 5){
        println("energia: $i%")
    }
    //rangos descendentes
    for (countdown in 10 downTo 1){
        println("Despegue en: $countdown")
    }
    
    for (salaFuneral in salasFuneral){
        if (salaFuneral=="D-11")continue
        if (salaFuneral=="D13")break
        println("Entrando a $salaFuneral")
    }
}