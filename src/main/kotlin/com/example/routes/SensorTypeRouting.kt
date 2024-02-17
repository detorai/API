package com.example.routes
import com.example.Services.SensorTypeService
import com.example.models.SensorType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sensorTypeRouting(sensorTypeService: SensorTypeService){
    get("/sensor_type/{model?}"){
        val model = call.parameters["model"]?.toInt()
        if (model != null){
            val sensorTypeResult = sensorTypeService.findByModel(model = model)
            call.respond(sensorTypeResult!!)
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid model parameter")
        }
    }
    post ("/sensor_type"){
        val sensorTypeData = call.receive<SensorType>()

        val sensorResult = sensorTypeService.createSensorType(sensorTypeData)
        call.respond(sensorResult!!)
    }
    delete ("/sensor_type/{model?}"){
        val model = call.parameters["model"]?.toInt()
        val sensorTypeIsDelete = sensorTypeService.deleteSensorType(model!!)
        if (sensorTypeIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
}