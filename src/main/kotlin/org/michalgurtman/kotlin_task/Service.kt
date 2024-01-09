package org.michalgurtman.kotlin_task

import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths

@Service
class Service {

    fun processDataFromFile(filePath: String): List<NameData> {
        
        return try {
            val lines = Files.readAllLines(Paths.get(filePath))
            if (lines.size <= 3) {
                lines.map { line ->
                    NameData(line.substring(0, 5), line.substring(5).split(","))
                }
            } else {
                List(lines.size) { index ->
                    val name = "name${index + 1}"
                    val data = if (index % 2 != 0) {
                        lines.filterIndexed { i, _ -> i % 2 == 1 }.joinToString("") { it.trim().last().toString() }
                    } else {
                        lines.filterIndexed { i, _ -> i % 2 == 0 }.joinToString("") { it.trim().last().toString() }
                    }

                    NameData(name, listOf(data))
                }
            }
        } catch (e: Exception) {
            println("Error processing file: ${e.message}")
            emptyList()
        }
    }
}
