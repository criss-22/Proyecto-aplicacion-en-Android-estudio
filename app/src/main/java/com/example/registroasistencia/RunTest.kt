package com.example.registroasistencia

fun main() {
    val r = Test.probarListarSync()
    val x = Test.probarMisionSync()

    println("Resultado ->")
    println(r.body())

    println("Resultado ->")
    println(x.body())
}