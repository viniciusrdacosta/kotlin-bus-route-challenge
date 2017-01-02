package com.goeuro.challenge.controller.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BusRouteResponse(@JsonProperty("dep_sid") val departureId: Int,
                       @JsonProperty("arr_sid") val arrivalId: Int,
                       @JsonProperty("direct_bus_route") val hasDirectRoute: Boolean)
