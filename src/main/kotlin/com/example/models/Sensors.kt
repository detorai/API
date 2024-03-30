package com.example.models

import kotlinx.serialization.Serializable

import org.jetbrains.exposed.sql.Table

@Serializable
data class Sensor(
    val sensor_id:Int,
    val sensor_name:String
)

object Sensors : Table(){
    val sensor_id = integer("sensor_model").autoIncrement()
    val sensor_name = varchar("sensor_type", length = 32)
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(sensor_id)
}