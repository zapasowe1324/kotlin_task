package org.michalgurtman.kotlin_task

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(private val service: Service) {

    @GetMapping("/run")
    fun runApp(): List<NameData>{
        return service.processDataFromFile("D:\\projekty\\kotlin_task\\src\\main\\resources\\file.txt")
    }
}