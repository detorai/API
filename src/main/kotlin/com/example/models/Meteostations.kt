package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.postgresql.geometric.PGpoint


@Serializable
data class Point(val latitude:Double, val longitude:Double)

@Serializable
data class Meteostation(
    val station_id:Int,
    val station_name:String,
    val longitude:String,
    val latitude:String,
    val station_hight_above_sea:Int,
    val station_county:String
)
object Meteostations: Table(){
    val station_id = integer("id")
    val station_name=varchar("station_name", length = 50)
    val longitude = pgpoint("longitude")
    val latitude = pgpoint("latitude")
    val station_hight_above_sea=integer("station_hight_above_sea")
    val station_county=varchar("station_address", length = 50)
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(station_id)

}
fun Table.pgpoint(name:String) : Column<String> = registerColumn(name, PGpointType())
class PGpointType():ColumnType(){

    override fun sqlType(): String = buildString{
        append("PGpoint")
    }

    override fun valueToDB(value: Any?): Any {
        val point = (value as Point)
        val pgpoint = PGpoint()
        pgpoint.x = point.latitude
        pgpoint.y = point.longitude
        return pgpoint
    }
    override fun valueFromDB(value: Any): Any {
        val pgpoint = (value as PGpoint)
        
        return Point(pgpoint.x, pgpoint.y)
    }

}
