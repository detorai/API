package com.example.dao.SensorTypes

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.SensorMeasurement
import com.example.models.Sensors_Measurements
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class TypeDaoImpl:TypeDaoFacade {
    private fun resultRowToType(row: ResultRow) =
        SensorMeasurement(sensor_id = row[Sensors_Measurements.sensor_id], type_id = row[Sensors_Measurements.type_id], measurement_formula = row[Sensors_Measurements.measurement_formula])
    override suspend fun findById(sensorId: Int): List<SensorMeasurement> = dbQuery{
        Sensors_Measurements.select {Sensors_Measurements.sensor_id eq sensorId}.map(::resultRowToType)
    }

    override suspend fun createSensorMeasurement(sensorMeasurement: SensorMeasurement): SensorMeasurement? = dbQuery{
        val insertSensorType = Sensors_Measurements.insert {
            it[Sensors_Measurements.sensor_id] = sensorMeasurement.sensor_id
            it[Sensors_Measurements.type_id] = sensorMeasurement.type_id
            it[Sensors_Measurements.measurement_formula] = sensorMeasurement.measurement_formula
        }
        insertSensorType.resultedValues?.singleOrNull()?.let(::resultRowToType)
    }

    override suspend fun deleteSensorMeasurement(sensorId: Int): Boolean = dbQuery{
        Sensors_Measurements.deleteWhere { Sensors_Measurements.sensor_id eq sensorId } > 0
    }


}
val sensorTypeDao = TypeDaoImpl()

