package com.goeuro.challenge.io

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class FileManagerTest {

    @Test fun shouldReturnDefaultRouteData() {
        assertThat(FileManager().routesData())
                .containsExactlyElementsOf(listOf("0 0 1 2 3 4", "1 3 1 6 5", "2 0 6 4"))
    }

    @Test fun shouldThrowRuntimeExceptionWhenAnyRouteHaveDuplicatedStation() {
        assertThatThrownBy({ FileManager(filePath = "data/duplicated-station-data").routesData() })
                .isInstanceOf(RuntimeException::class.java)
                .hasMessageContaining("Invalid Data: Route have duplicated station")
    }

    @Test fun shouldNotHaveNumberOfRoutesDifferentThanDefined() {
        assertThatThrownBy(({ FileManager(filePath = "data/invalid-number-routes-data").routesData() }))
                .isInstanceOf(RuntimeException::class.java)
                .hasMessageContaining("Number of routes expected 2 is different than returned 3")
    }

}