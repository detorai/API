package com.example.routes

import com.example.Services.MeteostationService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.meteostationRouting(meteostationService: MeteostationService){
    get("/meteostation/{station_id?}"){
        val station_id = call.parameters["station_id"]?.toInt()
        if (station_id != null){
            val meteostationResult = meteostationService.findByStation(station_id)
            call.respond(meteostationResult!!)
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid model parameter")
        }
    }
    delete("/meteostation/{station_id?}"){
        val stationId = call.parameters["station_id"]?.toInt()
        val meteostationIsDelete = meteostationService.deleteStation(stationId!!)
        if (meteostationIsDelete) call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.NotFound)
    }
}