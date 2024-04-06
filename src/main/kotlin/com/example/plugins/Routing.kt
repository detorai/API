package com.example.plugins

import com.example.Services.*
import com.example.routes.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        sensorRouting(sensorService)
        sensorTypeRouting(sensorTypeService)
        measurementTypeRouting(measurementTypeService)
        measurementRouting(measurementService)
        meteostationRouting(meteostationService)
        meteostationSensRouting(meteostationsSensService)
    }

}
