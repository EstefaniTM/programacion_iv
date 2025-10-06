package com.example.basics

fun main(){
    println("VARIABLES")
    val planeta = "Tatooine"
    var jedi = "Anakin"

    jedi = "Obiwan"

    println("Tipos de variables")
    println("Tipos de numéricos")
    println("Tipo Entero")
    val edad: Int = 25
    println(edad)

    println("Tipo Double")
    val altura: Double = 25.5
    println(altura)

    println("Tipo Float")
    val peso: Float = 25.5f
    println(peso)

    println("Tipo  Long")
    val poblacion: Long = 20000000_000L
    println(poblacion)

    println("Tipo  Texto")
    val nombre: String = "Obi-Wan Kenobi"
    println(nombre)

    println("Tipo  Char")
    //val inicial: Char = "0"
    //println(inicial)

    println("Tipo  Lógico")
    val esJedi: Boolean = true
    println(esJedi)

    println("Nulidad")
    val apellido: String? = ""
    println(apellido)

    println("Nulidad")
    val ciudad: String? = ""
    println(ciudad?.length)

    println("Operación de aserción no null")
    val longitudSegura = apellido!!.length

    println("Interpolación de strings")
    val nombrePrincesa: String = "Leia"
    val edadPrincesa: Int = 19
    val planetaPrincesa: String = "Alderan"

    println("${nombrePrincesa.uppercase()} nacio en ${planetaPrincesa}")
    println("En 10 años tendra : ${edadPrincesa+10} anios")

    println("String Multilinea")
    val mensaje: String = """
        Querido $nombre
        Tu mision en $planeta
        ha sido completada exitosamente
        Que la fuera te acompañe!
    """
    println(mensaje)

    println("Conversiones")
    val textoEdad: String = "25"
    val edadConvertida: Int = textoEdad.toInt()
    println("edadConvertida")

    val numero: Double = 50.8
    val numeroConvertido: String = numero.toString()
    println(numeroConvertido)
}