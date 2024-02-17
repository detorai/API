package com.example.models

import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.IColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.json
import org.postgresql.geometric.PGpoint
import org.postgresql.util.PGobject
import kotlin.reflect.KProperty


@Serializable
data class Point(val latitude:Double, val longitude:Double)

@Serializable
data class Meteostation(
    val station_id:Int,
    val station_coord:String,
    val station_z:String,
    val station_address:String,
    val station_name:String

)
object Meteostations: Table(){
    val station_id = integer("id")
    val station_coord = pgpoint("station_coord")
    val station_z=varchar("station_z", length = 255)
    val station_address=varchar("station_address", length = 255)
    val station_name=varchar("station_name", length = 255)
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
