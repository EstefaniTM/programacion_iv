package tarea

/*
* Suma de múltiplos de 3
Pide N y suma los múltiplos de 3 desde 1..N.
*  */

fun main(){
    var contador = 1
    var multiplos = 0

    println("Dame un numero random: ")
    var N = readln().toInt()

    while (contador<=N){
        if(contador % 3 == 0){
            multiplos = multiplos + contador
        }
        contador++

    }
    println()
    println(multiplos)

}