package com.example.routes


import com.example.Services.SensorService
import com.example.models.Sensor
import io.ktor.http.*
import io.ktor.http.cio.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sensorRouting(sensorService: SensorService){
    get("/sensors/{model?}"){
        val model = call.parameters["model"]
        val sensorResult=sensorService.findByModel(model = model!!)
        call.respond(sensorResult!!)
    }
    post("/sensors") {
        val sensorData = call.receive<Sensor>()

        val sensorResult = sensorService.createSensor(sensorData)
        call.respond(sensorResult!!)
    }
    delete("/sensors/{sensor_id?}"){
        val sensorID= call.parameters["sensor_id"]?.toInt()
        val sensorIsDelete = sensorService.deleteSensor(sensorID!!)
        if (sensorIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }

}