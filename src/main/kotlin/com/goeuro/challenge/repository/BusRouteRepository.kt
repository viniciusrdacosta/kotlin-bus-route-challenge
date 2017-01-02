package com.goeuro.challenge.repository

import com.goeuro.challenge.io.FileManager
import com.goeuro.challenge.model.BusRoute
import org.springframework.stereotype.Repository

@Repository
class BusRouteRepository(val manager: FileManager) {

    private val routes = routes()

    fun getDirectRoutesBetweenStations(from : Int, to: Int): List<BusRoute>{
        return routes.filter { route -> hasDirectRoute(from, to, route) }
    }

    private fun routes() : List<BusRoute> {
        return manager.routesData().map { toBusRoute(it) }
    }

    private fun toBusRoute(routes: String): BusRoute {
        val route = routes.split(regex = "\\s+".toRegex())
        val routeId = route.first().toInt()
        return BusRoute(routeId, route.drop(1).map(String::toInt))
    }

    private fun hasDirectRoute(from: Int, to: Int, route: BusRoute) = (
        route.stations.containsAll(listOf(from, to))
        && route.stations.indexOf(from) < route.stations.indexOf(to)
    )
}

