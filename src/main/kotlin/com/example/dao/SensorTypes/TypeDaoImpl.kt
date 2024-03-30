package com.example.dao.SensorTypes

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.SensorMeasurement
import com.example.models.SensorMeasurements
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class TypeDaoImpl:TypeDaoFacade {
    private fun resultRowToType(row: ResultRow) =
        SensorMeasurement(sensor_id = row[SensorMeasurements.sensor_id], type_id = row[SensorMeasurements.type_id], measurement_formula = row[SensorMeasurements.measurement_formula])
    override suspend fun findById(sensorId: Int): List<SensorMeasurement> = dbQuery{
        SensorMeasurements.select {SensorMeasurements.sensor_id eq sensorId}.map(::resultRowToType)
    }

    override suspend fun createSensorMeasurement(sensorMeasurement: SensorMeasurement): SensorMeasurement? = dbQuery{
        val insertSensorType = SensorMeasurements.insert {
            it[SensorMeasurements.sensor_id] = sensorMeasurement.sensor_id
            it[SensorMeasurements.type_id] = sensorMeasurement.type_id
            it[SensorMeasurements.measurement_formula] = sensorMeasurement.measurement_formula
        }
        insertSensorType.resultedValues?.singleOrNull()?.let(::resultRowToType)
    }

    override suspend fun deleteSensorMeasurement(sensorId: Int): Boolean = dbQuery{
        SensorMeasurements.deleteWhere { SensorMeasurements.sensor_id eq sensorId } > 0
    }


}
val sensorTypeDao = TypeDaoImpl()

