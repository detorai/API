package com.example.dao.SensorTypes

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.Sensors.SensorDaoImpl
import com.example.models.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime

class TypeDaoImpl:TypeDaoFacade {
    private fun resultRowToType(row: ResultRow) =
        SensorType(sensor_model = row[Sensor_types.sensor_model], type_id = row[Sensor_types.type_id])

    override suspend fun findByModel(model: Int): List<SensorType>? = dbQuery {
        Sensor_types.select { Sensor_types.sensor_model eq model }.map(::resultRowToType)
    }

    override suspend fun createSensorType(sensorType: SensorType): SensorType? = dbQuery {
        val insertSensorType = Sensor_types.insert {
            it[Sensor_types.sensor_model] = sensorType.sensor_model
            it[Sensor_types.type_id] = sensorType.type_id
        }
        insertSensorType.resultedValues?.singleOrNull()?.let(::resultRowToType)

    }

    override suspend fun deleteSensorType(model: Int): Boolean = dbQuery {
       Sensor_types.deleteWhere { Sensor_types.sensor_model eq model } > 0
    }

}
val sensorTypeDao = TypeDaoImpl()
