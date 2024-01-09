package org.michalgurtman.kotlin_task

import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths


class ServiceTests {



    @Test
    fun testProcessingDataWithLessOrEqualTo3Lines() {

        val mockFilePath = "D:\\projekty\\kotlin_task\\src\\main\\resources\\file.txt"
        val mockFileContent = listOf("AdriaSome,data,additional", "MariaData,Another")

        val service = Service()

        mockkStatic(Files::class)
        every { Files.readAllLines(Paths.get(mockFilePath)) } returns mockFileContent

        val result = service.processDataFromFile(mockFilePath)

        assert(result.size == 2)
        assert(result[0] == NameData("Adria", listOf("Some", "data", "additional")))
        assert(result[1] == NameData("Maria", listOf("Data", "Another")))

    }

    @Test
    fun testProcessingDataWithMoreThan3Lines() {

        val mockFilePath = "D:\\projekty\\kotlin_task\\src\\main\\resources\\file.txt"
        val mockFileContent = listOf("name1", "name2", "name3", "name4")

        val service = Service()

        mockkStatic(Files::class)
        every { Files.readAllLines(Paths.get(mockFilePath)) } returns mockFileContent

        val result = service.processDataFromFile(mockFilePath)

        assert(result.size == 4)
        assert(result[0] == NameData("name1", listOf("13")))
        assert(result[1] == NameData("name2", listOf("24")))
        assert(result[2] == NameData("name3", listOf("13")))
        assert(result[3] == NameData("name4", listOf("24")))
    }

    @Test
    fun testErrorHandling() {

        val mockFilePath = "random/path"
        val service = Service()

        val result = service.processDataFromFile(mockFilePath)

        assert(result.isEmpty())
    }
}
