package com.goeuro.challenge.io

import org.springframework.stereotype.Component
import java.io.File

@Component
class FileManager(val filePath: String = "data/sample-data") {

    fun routesData(): List<String> {
        val path = this.javaClass.classLoader.getResource(filePath).path
        val fileContent = File(path).readLines(charset = Charsets.UTF_8)
        return routes(fileContent)
    }

    private fun routes(fileContent: List<String>): List<String> {
        val routes = fileContent.drop(1)
        validateFileContent(fileContent.first().toInt(), routes)
        return routes
    }

    private fun validateFileContent(expectedNumberOfRoutes: Int, routes: List<String>) {
        validateNumberOfRoutes(expectedNumberOfRoutes, routes.size)
        validateDuplicateStationsInSameRoute(routes)
    }

    private fun validateDuplicateStationsInSameRoute(routes: List<String>) {
        val duplicatedStations = routes.filter { route -> hasDuplicatedStations(route) }.count()
        if (duplicatedStations > 0) {
            throw RuntimeException("Invalid Data: Route have duplicated station")
        }
    }

    private fun validateNumberOfRoutes(expectedNumberOfRoutes: Int, actualNumberOfRoutes: Int) {
        if (expectedNumberOfRoutes.compareTo(actualNumberOfRoutes) != 0) {
            throw RuntimeException("Number of routes expected $expectedNumberOfRoutes is different than returned $actualNumberOfRoutes")
        }
    }

    private fun hasDuplicatedStations(route: String): Boolean {
        val splitRoutes = route.drop(1).split(regex = "\\s+".toRegex())
        return splitRoutes.distinct().count() < splitRoutes.count()
    }
}