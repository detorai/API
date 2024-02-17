package com.example.models

import com.example.models.Sensors.autoIncrement
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class SensorType(
    val sensor_model:Int,
    val type_id:Int
)
object Sensor_types: Table(){
    override val tableName: String
        get() = "sensor_types"
    val sensor_model = integer("sensor_model")
    val type_id = integer("type_id")
}