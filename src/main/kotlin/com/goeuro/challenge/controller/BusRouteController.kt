package com.goeuro.challenge.controller

import com.goeuro.challenge.controller.response.BusRouteResponse
import com.goeuro.challenge.service.BusRouteService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BusRouteController(val service: BusRouteService) {

    @RequestMapping(path = arrayOf("/direct"), method = arrayOf(RequestMethod.GET))
    fun directRoute(@RequestParam("dep_sid") departureId: Int,
                    @RequestParam("arr_sid") arrivalId: Int): BusRouteResponse {
        val hasDirectRoute = service.hasDirectRouteBetweenStations(departureId, arrivalId)
        return BusRouteResponse(departureId, arrivalId, hasDirectRoute)
    }
}

