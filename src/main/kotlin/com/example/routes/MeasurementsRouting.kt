package com.example.routes

import com.example.dao.Measurements.MeasurementsDaoImpl
import com.example.models.Measurement
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.measurementRouting(measurementService: MeasurementsDaoImpl){
    get("/measurements/{station?}"){
        val station = call.parameters["station"]?.toInt()
        if (station != null){
            val measurementTypeResult = measurementService.findByStation(station = station)
            call.respond(measurementTypeResult!!)
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid model parameter")
        }
    }
    post("/measurements") {
        val measurementData = call.receive<Measurement>()

        val insertResult = measurementService.createMeasurement(measurementData)

        call.respond(insertResult!!)
    }
    delete("/measurements/{station?}"){
        val stationId = call.parameters["station"]?.toInt()
        val measurementIsDelete = measurementService.deleteMeasurement(stationId!!)
        if (measurementIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
}