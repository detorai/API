package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class SensorMeasurement(
    val sensor_id:Int,
    val type_id:Int,
    val measurement_formula: String
)
object Sensors_Measurements: Table(){
    val sensor_id = integer("sensor_id")
    val type_id = integer("type_id")
    val measurement_formula = varchar("measurement_formula", length = 255)
}