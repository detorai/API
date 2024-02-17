package com.example.models

import kotlinx.serialization.Serializable

import org.jetbrains.exposed.sql.Table

@Serializable
data class Sensor(
    val sensor_model:Int,
    val sensor_type:String
)

object Sensors : Table(){
    val sensor_model = integer("sensor_model").autoIncrement()
    val sensor_type = varchar("sensor_type", length = 255)
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(sensor_model)
}