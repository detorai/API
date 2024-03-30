package com.example.dao.MeasurementTypes

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Measurement_type
import com.example.models.Measurements_type
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class MeasurementTypeDaoImpl:MeasurementTypeDaoFacade {
    private fun resultRowToMeasurement_type(row: ResultRow) = Measurement_type(
        type_id = row[Measurements_type.type_id],
        type_name = row[Measurements_type.type_name],
        type_units = row[Measurements_type.type_units]
    )
    override suspend fun findByTypeId(type_id: Int): Measurement_type? =dbQuery {
        Measurements_type.select{Measurements_type.type_id eq type_id}.map(::resultRowToMeasurement_type).singleOrNull()
    }

    override suspend fun createMeasurementType(measurement_type: Measurement_type): Measurement_type? = dbQuery{
        val  insertMeasurement = Measurements_type.insert {
            it[Measurements_type.type_id]=measurement_type.type_id
            it[Measurements_type.type_name]=measurement_type.type_name
            it[Measurements_type.type_units]=measurement_type.type_units
        }
        insertMeasurement.resultedValues?.singleOrNull()?.let(::resultRowToMeasurement_type)
    }

    override suspend fun deleteMeasurementType(type_id: Int): Boolean = dbQuery{
        Measurements_type.deleteWhere { Measurements_type.type_id eq type_id } > 0
    }

}
val measurementDao = MeasurementTypeDaoImpl()