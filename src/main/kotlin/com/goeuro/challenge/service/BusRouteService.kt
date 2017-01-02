package com.goeuro.challenge.service

import com.goeuro.challenge.repository.BusRouteRepository
import org.springframework.stereotype.Component

@Component
class BusRouteService(val repository: BusRouteRepository) {

    fun hasDirectRouteBetweenStations(from: Int, to: Int): Boolean {
        return repository.getDirectRoutesBetweenStations(from, to).isNotEmpty()
    }
}