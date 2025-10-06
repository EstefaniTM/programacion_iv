package com.example.myapplication


fun main(){
    println("bucles")
    val jedis = listOf("luke", "leia", "obi-wan", "yoda", "ahsoka")
    for ((index,jedi) in jedis.withIndex()){
        println("${index+1}.$jedi")
    }
    // rangos de paso
    for (i in 0 .. 20 step 5){
        println("energia: $i%")
    }
    //rangos descendentes
    for (countdown in 10 downTo 1){
        println("Despegue en: $countdown")
    }

    //control de flujo
    for (jedi in jedis){
        if(jedi == "obi-wan") continue // saltar esta iteracion
        if(jedi == "yoda") break
        println("entrando a $jedi")
    }
}