package com.example.basics

import java.sql.DriverManager.println


fun main(){
    println("!Operadores logicos!!")
    val montoInicial: Int = 50
    val papeles: Boolean = true
    val iniciarFuneral = montoInicial>=100 && papeles
    val informarPago = montoInicial<=100 && !papeles
    println("Se iniciara el funeral ${iniciarFuneral}")
    println("Se llamara para informar que el pago esta incorrecto ${informarPago}")
}
