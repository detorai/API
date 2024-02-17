package com.example.dao.MeasurementTypes

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.Sensors.SensorDaoImpl
import com.example.models.Measurement_type
import com.example.models.Measurement_types
import com.example.models.SensorType
import com.example.models.Sensor_types
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class MeasurementTypeDaoImpl:MeasurementTypeDaoFacade {
    private fun resultRowToMeasurement_type(row: ResultRow) = Measurement_type(type_id = row[Measurement_types.type_id], type_name =  row[Measurement_types.type_name], type_formula = row[Measurement_types.type_formula], type_unit = row[Measurement_types.type_unit])
    override suspend fun findByTypeId(type_id: Int): Measurement_type?=dbQuery {
        Measurement_types.select{Measurement_types.type_id eq type_id}.map(::resultRowToMeasurement_type).singleOrNull()
    }

    override suspend fun createMeasurementType(measurement_type: Measurement_type): Measurement_type? = dbQuery{
        val  insertMeasurement = Measurement_types.insert {
            it[Measurement_types.type_id]=measurement_type.type_id
            it[Measurement_types.type_name]=measurement_type.type_name
            it[Measurement_types.type_unit]=measurement_type.type_unit
            it[Measurement_types.type_formula]=measurement_type.type_formula
        }
        insertMeasurement.resultedValues?.singleOrNull()?.let(::resultRowToMeasurement_type)
    }

    override suspend fun deleteMeasurementType(type_id: Int): Boolean = dbQuery{
        Measurement_types.deleteWhere { Measurement_types.type_id eq type_id } > 0
    }

}
val measurementDao = MeasurementTypeDaoImpl()