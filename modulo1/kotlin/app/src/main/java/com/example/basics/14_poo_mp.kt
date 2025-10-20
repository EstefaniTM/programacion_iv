package com.example.basics

data class funerariaServicio(val nombre: String, val celular: Int, val preciopagado: Int){
    val rango: String get() = when {
        preciopagado >= 100 -> "Ataud premiun"
        preciopagado >= 50 -> "Ataud normal"
        else ->{"Precio de ataud no iniciado"}
    }
    fun puedecontratar(): Boolean = preciopagado >= 50
    fun nopuedepagar(): Boolean = preciopagado>=20
}

fun main(){
    val usuario = funerariaServicio("Juan", 980894657, 100)
    println(usuario)
    val (nombre, celular, preciopagado) = usuario
    println("Nombre del usuario $nombre, con el selular $celular y el precio que dio de $preciopagado")
    val mario = usuario.copy(nombre="Mario", celular=98789965, preciopagado=50)

    println("Datos de Mario ${mario.rango}")
    println("Mario puede pagar? ${mario.puedecontratar()}")
}