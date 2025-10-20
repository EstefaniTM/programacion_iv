package tarea

/*
* Contador de pares hasta N
Pide N y cuenta cuántos números pares hay entre 1 y N.
* */

fun main(){
    var contador = 1
    println("Dame un numero: ")
    var N = readln().toInt()

    var pares = 0

    while (contador<=N){
        if (contador % 2 == 0) {
            pares = pares + 1
        } else{
            println("Es impar")
        }
        contador++}

    println(pares)
}