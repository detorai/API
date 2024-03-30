package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Measurement_type(
    val type_id:Int,
    val type_name:String,
    val type_units:String,
)
object Measurements_type: Table(){
    val type_id = integer("type_id").autoIncrement()
    val type_name = varchar("type_name", length = 31)
    val type_units = varchar("type_units", length = 4)
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(Measurements_type.type_id)
}
