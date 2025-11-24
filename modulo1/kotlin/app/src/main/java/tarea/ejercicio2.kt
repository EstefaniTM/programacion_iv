package tarea

/*
* Entrada de cine con descuento por edad
Pide edad. Base $5.


<12 → $3


≥65 → $4


Si no, $5.
* */

fun main(){
    var contador = 1

    while (contador <= 3){
        print("Dame tu edad: ")
        var edad = readln().toInt()

        if(edad <12){
            print("Su descuento es de 2 dolares, su pago es 3")
        } else if (edad>=65){
            print("Su descuento ees de 1 dolar, su pago es de 4")
        } else {
            print("Su pago es de 5")
        }

        contador++
        println()
    }

}