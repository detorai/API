package com.example.routes

import com.example.dao.MeteostationSensors.MeteostationSenDaoImpl
import com.example.models.Request_Meteostations_Sensors
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.meteostationSensRouting(meteostationSensService:MeteostationSenDaoImpl){
        get("/meteostation_sens/{inv_numb?}"){
            val inv_numb = call.parameters["inv_numb"]?.toInt()
            if (inv_numb != null){
                val meteostationSensResult = meteostationSensService.findByInvNumb(sensor_inv_numb = inv_numb)
                call.respond(meteostationSensResult!!)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid model parameter")
            }
        }
        post("/meteostation_sens") {
            val meteostationSensData = call.receive<Request_Meteostations_Sensors>()
            val insertResult = meteostationSensService.createInvNumb(meteostationSensData)
            call.respond(insertResult!!)
        }
        delete("meteostation_sens/{inv_numb?}") {
            val sensor_inv_numb = call.parameters["inv_numb"]?.toInt()
            val measurementIsDelete = meteostationSensService.deleteInvNumb(sensor_inv_numb!!)
            if (measurementIsDelete) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }
}