package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp



@Serializable
data class Measurement(
    val measurement_value:Float,
    val measurement_type:Int,
    val measurement_ts:String,
    val sensor_inventory_number:Int
)
object Measurements: Table(){
    val measurement_value = float("measurement_value")
    val measurement_type = integer("measurement_type")
    val measurement_time=timestamp("measurement_ts")
    val sensor_inventory_number = integer("sensor_inventory_number")
}


