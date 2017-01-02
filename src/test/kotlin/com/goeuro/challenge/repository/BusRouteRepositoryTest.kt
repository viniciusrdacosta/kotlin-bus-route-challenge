package com.goeuro.challenge.repository

import com.goeuro.challenge.io.FileManager
import com.goeuro.challenge.model.BusRoute
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class BusRouteRepositoryTest {

    @Test fun shouldGetAllDirectRoutesFromDepartureAndArrival() {
        val fileManager : FileManager = mock {
            on { routesData() } doReturn listOf("3", "0 0 1 2 3 4", "1 3 1 6 5", "2 0 6 4")
        }

        val repository = BusRouteRepository(fileManager)

        assertThat(repository.getDirectRoutesBetweenStations(3, 6))
                .containsExactlyElementsOf(listOf(BusRoute(1, listOf(3, 1, 6, 5))))
    }
}

