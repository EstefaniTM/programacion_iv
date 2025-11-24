package com.example.basics

enum class TipoServicio(val descripcion: String, val preciofuneral: Int ){
    VELORIO("Velorio", 40){
        override fun description()="Velorio, algo cotidiano en los funerales"
    },
    ENTIERRO("Entierro", 100){
        override fun description()="Se lleva con musica del velorio hasta el entierro"
    };
    abstract fun description(): String
}


class ServicioFunerario(val tipo: TipoServicio, val usuario: String){
    fun activar() = "El servicio ${tipo.descripcion} del cliente $usuario comenzo"
    fun info() = "${tipo.description()} - tipo descripcion ${tipo.descripcion}"
}

fun main(){

    val servicioJuan = ServicioFunerario(TipoServicio.VELORIO, usuario = "Juan")
    println(servicioJuan.activar())
    println(servicioJuan.info())
}