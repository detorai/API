package com.example.routes

import com.example.Services.MeasurementTypeService
import com.example.dao.MeasurementTypes.MeasurementTypeDaoImpl
import com.example.models.Measurement_type
import com.example.models.Measurement_types
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.measurementTypeRouting(measurementTypeService: MeasurementTypeDaoImpl){
    get("/measurement_type/{id?}"){
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
    delete ("/measurement_type/{id?}"){
        val measurementType = call.parameters["id"]?.toInt()
        val measurementTypyIsDelete = measurementTypeService.deleteMeasurementType(measurementType!!)
        if (measurementTypyIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)

    }
}