package com.example.routes
import com.example.Services.SensorTypeService
import com.example.models.SensorMeasurement
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sensorTypeRouting(sensorTypeService: SensorTypeService){
    get("/sensor_measurement/{id?}"){
        val sensor = call.parameters["id"]?.toInt()
        if (sensor != null){
            val sensorTypeResult = sensorTypeService.findByModel(sensorId = sensor)
            call.respond(sensorTypeResult!!)
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid model parameter")
        }
    }
    post ("/sensor_measurement"){
        val sensorMeasurementData = call.receive<SensorMeasurement>()

        val sensorResult = sensorTypeService.createSensorMeasurement(sensorMeasurementData)
        call.respond(sensorResult!!)
    }
    delete ("/sensor_measurement/{id?}"){
        val sensor = call.parameters["id"]?.toInt()
        val sensorTypeIsDelete = sensorTypeService.deleteSensorType(sensor!!)
        if (sensorTypeIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
}