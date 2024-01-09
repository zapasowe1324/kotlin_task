package org.michalgurtman.kotlin_task

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinTaskApplication

fun main(args: Array<String>) {
    runApplication<KotlinTaskApplication>(*args)
}
