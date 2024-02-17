package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Measurement_type(
    val type_id:Int,
    val type_name:String,
    val type_unit:String,
    val type_formula:String
)
object Measurement_types: Table(){
    val type_id = integer("type_id").autoIncrement()
    val type_name = varchar("type_name", length = 255)
    val type_unit = varchar("type_unit", length = 20)
    val  type_formula = varchar("type_formula", length = 255)
    override val primaryKey: PrimaryKey
        get() = PrimaryKey(Measurement_types.type_id)
}
