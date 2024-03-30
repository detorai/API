package com.example.routes

import com.example.dao.MeasurementTypes.MeasurementTypeDaoImpl
import com.example.models.Measurement_type
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.measurementTypeRouting(measurementTypeService: MeasurementTypeDaoImpl){
    get("/measurements_type/{id?}"){
        val id = call.parameters["id"]?.toInt()
        if (id != null){
            val measurementTypeResult = measurementTypeService.findByTypeId(type_id = id)
            call.respond(measurementTypeResult!!)
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid model parameter")
        }
    }
    post("/measurement_type") {
        val measurement_typeData = call.receive<Measurement_type>()

        val insertResult = measurementTypeService.createMeasurementType(measurement_typeData)

        call.respond(insertResult!!)
    }
    delete ("/measurements_type/{id?}"){
        val measurementType = call.parameters["id"]?.toInt()
        val measurementTypeIsDelete = measurementTypeService.deleteMeasurementType(measurementType!!)
        if (measurementTypeIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)

    }
}