package me.kjgleh.mycoroutine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MycoroutineApplication

fun main(args: Array<String>) {
    runApplication<MycoroutineApplication>(*args)
}
