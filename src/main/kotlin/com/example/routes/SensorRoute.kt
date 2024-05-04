package com.example.routes


import com.example.Services.SensorService
import com.example.models.Request_Sensor
import com.example.models.Sensor
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sensorRouting(sensorService: SensorService){
    get("/sensor/{id?}"){
        val id = call.parameters["id"]?.toInt()
        val sensorResult=sensorService.findById(sensorId = id!!)
        call.respond(sensorResult!!)
    }
    post("/sensor") {
        val sensorData = call.receive<Request_Sensor>()
        val sensorResult = sensorService.createSensor(sensorData)
        call.respond(sensorResult!!)
    }
    delete("/sensor/{id?}"){
        val sensorID= call.parameters["id"]?.toInt()
        val sensorIsDelete = sensorService.deleteSensor(sensorID!!)
        if (sensorIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
    get("/sensor"){
        call.respond(sensorService.allSensors()!!)
    }
    put("/sensor"){
        val sensorData= call.receive<Sensor>()
        val SensResault = sensorService.editSensor(sensorData)
        call.respond(SensResault)
    }
}