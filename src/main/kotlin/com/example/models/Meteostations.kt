package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table




@Serializable
data class Meteostation(
    val station_id:Int,
    val station_name:String,
    val longitude:Float,
    val latitude:Float,
    val station_hight_above_sea:Int,
    val station_county:String
)
object Meteostations: Table(){
    val station_id = integer("station_id")
    val station_name=varchar("station_name", length = 50)
    val longitude = float("longitude")
    val latitude = float("latitude")
    val station_hight_above_sea=integer("station_hight_above_sea")
    val station_county=varchar("station_country", length = 50)
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(station_id)
}
