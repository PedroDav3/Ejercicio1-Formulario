package com.dav3_ac.ejercicio1

import kotlin.math.pow
import kotlin.random.Random

fun main(){
    //println("Hola mundo soy yo")
    //sumaNumeros(num1 = 5, num2 = 15)
    operaNum(15, 7, object : operaciones{
        override fun suma(num1: Int, num2: Int ) {
            println("La suma de $num1 mas $num2 es igual a ${num1+num2}")

        }

        override fun resta(num1: Int, num2: Int) {
            println("La resta de $num1 mas $num2 es igual a ${num1-num2}")

        }
    })

    val miLamda: (Int, Int) -> Unit = { a,b -> println("La suma con lamda es: ${a+b} ") }

    operaNum(58,96,miLamda)
    operaNum(58,9) { a,b -> println("La multiplicacion con lamda es: ${a*b} ") }
    operaAleatorio {
        val resultado = it.toDouble().pow(3.0)
        println("El cubo de $it es: $resultado")
    }

    operaAleatorio {
        val resultado = it.toDouble().pow(2.0)
        println("El cuadrado de $it es: $resultado")
    }
}

fun sumaNumeros(num1: Int, num2: Int){
    println("La suma de $num1 mas $num2 es igual a ${num1+num2}")
}

interface operaciones{
    fun suma(a: Int, b:Int)
    fun resta(a: Int, b:Int)
}


fun operaNum(num1: Int, num2: Int, operacion: operaciones){
    operacion.suma(num1,num2)
    operacion.resta(num1,num2)
}

fun operaNum(num1: Int, num2: Int, operaciones: (Int, Int) -> Unit){
    operaciones(num1,num2)
}

fun operaAleatorio(operacion: (Int) -> Unit){
    val random = Random.nextInt(0,100)
    operacion(random)
}