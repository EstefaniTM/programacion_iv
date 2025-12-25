package com.example.basics

enum class TipoSable(val color: String, val poder: Int){
    AZUL("azul", 85){
        override fun description()="Sable tradicional de los jedi"
    },
    VERDE("verde", 90){
        override fun description()="Sable de los jedi consultares"
    },
    ROJO("rojo", 95){
        override fun description()="Sable de los Sith"
    },
    MORADO("morado", 95){
        override fun description() = "Sable equilibra luz y oscuridad"
    };
    abstract fun description(): String companion object{
        fun porColor(color: String) = entries.find {it.color==color}
    }
}

class SableDeLuz(val tipo: TipoSable, val portador: String){
    fun activar() = "!ZZrum¡ El sable color ${tipo.color} de $portador se enciende"
    fun info() = "${tipo.description()} - tipo poder ${tipo.poder}"
}

fun main(){
    val sableWindoo = SableDeLuz(TipoSable.MORADO, "Windoo")
    println(sableWindoo)
    println(sableWindoo.activar())
    println(sableWindoo.info())

    val sableLuke = SableDeLuz(TipoSable.VERDE, "Luke Skywalker")
    println(sableLuke)
    println(sableLuke.activar())
    println(sableLuke.info())
}