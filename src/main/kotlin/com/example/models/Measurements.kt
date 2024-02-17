package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp



@Serializable
data class Measurement(
    val station:Int,
    val measurement_type:Int,
    val measurement_value:Float,
    val sensor_model:Int,
    val measurement_time:String
)
object Measurements: Table(){
    val station = integer("station")
    val measurement_type = integer("measurement_type")
    val measurement_value = float("measurement_value")
    val sensor_model = integer("sensor_model")
    val measurement_time=timestamp("measurement_ts_new")
}


