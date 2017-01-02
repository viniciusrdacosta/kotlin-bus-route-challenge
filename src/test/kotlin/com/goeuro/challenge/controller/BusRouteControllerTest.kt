package com.goeuro.challenge.controller

import com.goeuro.challenge.service.BusRouteService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
class BusRouteControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var service: BusRouteService

    @Test
    fun shouldReturnBusRouteResponseWithDirectBusRoute() {

        whenever(service.hasDirectRouteBetweenStations(any(), any())).thenReturn(true)

        mockMvc.perform(get("/direct").param("dep_sid", "3").param("arr_sid", "6"))
                .andExpect { content().contentType("application/json;charset=UTF-8") }
                .andExpect { status().isOk }
                .andExpect { jsonPath("$.dep_sid").isNumber }
                .andExpect { jsonPath("$.dep_sid").value("3") }
                .andExpect { jsonPath("$.arr_sid").isNumber }
                .andExpect { jsonPath("$.arr_sid").value("6") }
                .andExpect { jsonPath("$.direct_bus_route").isBoolean }
                .andExpect { jsonPath("$.direct_bus_route").value("true") }
    }

    @Test
    fun shouldReturnBusRouteResponseWithoutDirectBusRoute() {

        whenever(service.hasDirectRouteBetweenStations(any(), any())).thenReturn(false)

        mockMvc.perform(get("/direct").param("dep_sid", "6").param("arr_sid", "3"))
                .andExpect { content().contentType("application/json;charset=UTF-8") }
                .andExpect { status().isOk }
                .andExpect { jsonPath("$.dep_sid").isNumber }
                .andExpect { jsonPath("$.dep_sid").value("6") }
                .andExpect { jsonPath("$.arr_sid").isNumber }
                .andExpect { jsonPath("$.arr_sid").value("3") }
                .andExpect { jsonPath("$.direct_bus_route").isBoolean }
                .andExpect { jsonPath("$.direct_bus_route").value("false") }
    }

}