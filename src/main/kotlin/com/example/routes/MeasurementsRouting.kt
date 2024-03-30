package com.example.routes

import com.example.dao.Measurements.MeasurementsDaoImpl
import com.example.models.Measurement
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.measurementRouting(measurementService: MeasurementsDaoImpl){
    get("/measurements/{inv_numb?}"){
        val inv_numb = call.parameters["inv_numb"]?.toInt()
        if (inv_numb != null){
            val measurementTypeResult = measurementService.findByInvNumb(sensor_inventory_number = inv_numb)
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
    delete("/measurements/{inv_numb?}"){
        val sensor_inv_numb = call.parameters["inv_numb"]?.toInt()
        val measurementIsDelete = measurementService.deleteMeasurement(sensor_inv_numb!!)
        if (measurementIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
}