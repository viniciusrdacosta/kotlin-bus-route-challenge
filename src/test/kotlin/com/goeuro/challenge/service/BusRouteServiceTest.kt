package com.goeuro.challenge.service

import com.goeuro.challenge.model.BusRoute
import com.goeuro.challenge.repository.BusRouteRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class BusRouteServiceTest {

    @Test fun shouldHaveDirectRouteFromDepartureToArrival() {
        val repository : BusRouteRepository = mock {
            on { getDirectRoutesBetweenStations(any(), any()) } doReturn listOf(BusRoute(1, listOf(3, 1, 6, 5)))
        }
        val service = BusRouteService(repository)
        assertThat(service.hasDirectRouteBetweenStations(1, 5)).isTrue()

    }

    @Test fun shouldNotHaveDirectRouteFromDepartureToArrival() {
        val repository : BusRouteRepository = mock {
            on { getDirectRoutesBetweenStations(any(), any()) } doReturn emptyList<BusRoute>()
        }
        val service = BusRouteService(repository)
        assertThat(service.hasDirectRouteBetweenStations(1, 5)).isFalse()

    }
}