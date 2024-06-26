package com.example.models

import kotlinx.serialization.Serializable

import org.jetbrains.exposed.sql.Table

@Serializable
data class Sensor(
    val sensor_id:Int,
    val sensor_name:String
)
data class Request_Sensor(
    val sensor_name: String
)

object Sensors : Table(){
    val sensor_id = integer("sensor_id").autoIncrement()
    val sensor_name = varchar("sensor_name", length = 32)
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(sensor_id)
}
val SensorList = mutableListOf(Sensors)