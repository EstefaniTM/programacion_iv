package tarea

/*
Semáforo peatonal
Pide color del semáforo ("rojo", "amarillo", "verde") y si el peatón presionó el botón ("si"/"no").


Si está verde y presionó → “Espera a rojo”.


Si está rojo → “Cruza”.


Si amarillo → “Prepárate”.


En otros casos → “Espera”.
* */

fun main(){
    var contador = 1

    while (contador<=3){
        print("Elige el color del semaforo(rojo, amarillo, verde): ")
        var color = readln().toLowerCase()
        print("Presiono el boton? si o no: ")
        var boton = readln().toLowerCase()

        if (color == "verde" && boton == "si"){
            print("Espera a rojo")
        } else if( color == "rojo"){
            print("cruza")
        } else if (color=="amarillo"){
            print("Preparate")
        }
        contador++
    }
}