package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

@Serializable
data class Meteostations_Sensors(
    val station_id: Int,
    val sensor_id: Int,
    val added_ts: String,
    val removed_ts: String,
    val sensor_inventory_number: Int
)
@Serializable
data class Request_Meteostations_Sensors(
    val station_id: Int,
    val sensor_id: Int,
)
object Meteostations_sensors: Table(){
    val station_id = integer("station_id")
    val sensor_id = integer("sensor_id")
    val added_ts = timestamp("added_ts").autoIncrement()
    val removed_ts = timestamp("removed_ts").autoIncrement()
    val sensor_inventory_number = integer("sensor_inventory_number").autoIncrement()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(sensor_inventory_number)
}